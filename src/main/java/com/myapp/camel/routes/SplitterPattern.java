package com.myapp.camel.routes;

import com.myapp.camel.bean.OrderItem;
import com.myapp.camel.bean.OrderItemConverter;
import com.myapp.camel.bean.OrderLineItems;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

//@Component
public class SplitterPattern extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("myJms:queue:combinedOrders")
        .routeId("splitter-route")
                .setProperty("totalPrice", jsonpath("$.total"))
                .setProperty("orderId", jsonpath("$.orderId"))

                .unmarshal().json(JsonLibrary.Jackson) // Now body is a Java object (likely Map)

                .log("Items being processed ${body}")

                .split().jsonpath("$.items[*]") // Splitting on items
                .streaming()
                .bean(OrderItemConverter.class, "mapToOrderItem")
//                .unmarshal().json(JsonLibrary.Jackson, OrderItem.class)

                .log("Items being processed ${body.productName}")

                .choice()
                .log("TotalPrice: ${exchangeProperty.totalPrice} (${exchangeProperty.totalPrice.class})")
                .when(simple("${body.productName} == 'Widget' && ${body.price} != null && ${body.price} > 100.0"))
                .marshal().json(JsonLibrary.Jackson)
                .to("myJms:queue:widgetHighValueOrders")

                .when(simple("${body.productName} == 'Widget'"))
                .marshal().json(JsonLibrary.Jackson)
                .to("myJms:queue:widgetOrders")

                .when(simple("${body.productName} == 'FreshItems'"))
                .marshal().json(JsonLibrary.Jackson)
                .to("myJms:queue:freshitemorders")

                .otherwise()
                .marshal().json(JsonLibrary.Jackson)
                .to("myJms:queue:otherorders")
                .endChoice()
                .end();
//        from("myJms:queue:combinedOrders")
//                .routeId("splitter-route")
//                .setProperty("totalPrice",jsonpath("$.total"))
//                .setProperty("orderId",jsonpath("$.orderId"))
//
//                .unmarshal().json(JsonLibrary.Jackson)
//                .convertBodyTo(String.class)
//
//                .log("Items being processed ${body}")
//                .split().jsonpath("$.items[*]")
//               // .log("jsonpath('${$.orderId}'")
//                .streaming()
//                .unmarshal().json(JsonLibrary.Jackson, OrderItem.class)
//                .log("Items being processed ${body.productName}")
//                .choice()
//
//                .when(simple("${body.productName} == 'Widget'"))
//                .to("myJms:queue:widgetHighValueOrders")
////                .when(simple("${body.productName} == 'Widget'"))
////                .to("myJms:queue:widgetOrders")
//                .when(simple("${body.productName} == 'FreshItems'"))
//                .to("myJms:queue:freshitemorders")
//                .otherwise()
//                .to("myJms:queue:otherorders")
//                //end our choice cbr
//                .endChoice()
//                // main route
//                .end();
//

        // Camel Bean Registries (ApplicationContext) CdiBeanRegistry (JakartaEE || JavaEE)
        // Camel Select Bean Methods
        // Camel bean method parameter binding

        // Enterprise Integration Patterns

        // Message Channel Patterns
        // Message Routing Patterns
        // Message Transformations
        // Error Handling Patterns
        // Deployment Patterns
        // from()
        // JavaDSL or XML DSL
        // CamelContext

        // Spring Boot (IOC Container)
        // Quarkus
        // Jakarta EE (CDI Container)
        // CBR
        // KCamel

        // 2 Aspects Good Design
        // Application Should Work
        // Flexible, Maintainable & Testable
        // Testing & Debugging





    }
}
