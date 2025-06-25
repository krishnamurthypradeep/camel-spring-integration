package com.myapp.camel.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.ConnectionFactory;
//import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jackson.avro.transform.AvroSchemaResolver;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean()
//    ActiveMQConnectionFactory activeMQConnectionFactory1(){
//        return new ActiveMQConnectionFactory("tcp://localhost:61616");
//    }

    @Bean("myJms")
    JmsComponent jmsComponent(ConnectionFactory connectionFactory){
        return JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
    }

    @Bean
    public com.fasterxml.jackson.databind.ObjectMapper objectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        // register Java 8 date/time module
        mapper.registerModule(new JavaTimeModule());
        // use ISO dates (e.g. "2025-06-25") instead of numeric timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

//    AvroSchemaResolver schemaResolver(){
//        return new DefaultAvr
//    }




}
