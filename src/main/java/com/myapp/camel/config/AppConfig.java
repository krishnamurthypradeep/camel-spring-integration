package com.myapp.camel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myapp.camel.avro.Order;
import jakarta.jms.ConnectionFactory;
//import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.avro.transform.AvroSchemaResolver;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean
//    RouteTemplateBuilder dynamicTemplate() {
//        return new RouteTemplateBuilder("dynamicRoute")
//                .parameter("input")
//                .parameter("output")
//                .routeTemplate(rt -> rt
//                        .from("{{input}}")
//                        .to("bean:commonProcessor")
//                        .to("{{output}}"));
//    }
//
//    // usage
//    from("direct:start")
//  .toD("route:dynamicRoute?input=file:in&output=activemq:queue:out");

//    @Bean()
//    ActiveMQConnectionFactory activeMQConnectionFactory1(){
//        return new ActiveMQConnectionFactory("tcp://localhost:61616");
//    }

    @Bean("myJms")
    JmsComponent jmsComponent(ConnectionFactory connectionFactory){

       JmsComponent jms=  JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);

       return jms;
    }

//    @Bean
//    public com.fasterxml.jackson.databind.ObjectMapper objectMapper() {
//        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
//        // register Java 8 date/time module
//        mapper.registerModule(new JavaTimeModule());
//        // use ISO dates (e.g. "2025-06-25") instead of numeric timestamps
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }
//@Bean
//public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//    return builder -> {
//        builder.modules(new JavaTimeModule());
//        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    };
//}
@Bean
public JacksonDataFormat jacksonDataFormatForOrder() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    JacksonDataFormat format = new JacksonDataFormat(Order.class);
    format.setObjectMapper(mapper);
    return format;
}

//    AvroSchemaResolver schemaResolver(){
//        return new DefaultAvr
//    }




}
