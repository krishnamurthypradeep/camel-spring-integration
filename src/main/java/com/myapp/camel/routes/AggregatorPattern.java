package com.myapp.camel.routes;

import com.myapp.camel.bean.OrderItem;
import com.myapp.camel.bean.OrderLineItems;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.leveldb.LevelDBAggregationRepository;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.AggregationRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Path;

@Component
public class AggregatorPattern extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        AggregationRepository myRepo =
                new LevelDBAggregationRepository("myrepo", "repo/myrepo.dat");

        from("myJms:queue:orders")
                .routeId("aggregator-route")
                .log("Received Orders ${body}")
                .unmarshal().json(JsonLibrary.Jackson, OrderItem.class)

                .setHeader("orderId",simple("${body.orderId}"))
        // corelation Identifier orderId
                .aggregate(header("orderId"),new MyAggregator())
                //.aggregationRepository()
                .completionTimeout(10000)
                .completionTimeoutCheckerInterval(1000)
                // persistence to manage
                .aggregationRepository(myRepo)

                .marshal().json(JsonLibrary.Jackson, OrderLineItems.class)
                .convertBodyTo(String.class)
                //.completionTimeout(5000)
                .to("myJms:queue:combinedOrders?jmsMessageType=Text");

    }
}


// In Different Ids become a separate group
// orderId = 1234 (A) orderId =4321 (B)
// For Group A 10s elapses it fires
// For Group B if
// If a third orderId appears starts (C)