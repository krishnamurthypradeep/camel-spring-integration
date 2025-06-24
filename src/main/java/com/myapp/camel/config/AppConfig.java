package com.myapp.camel.config;

import jakarta.jms.ConnectionFactory;
//import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean()
    ActiveMQConnectionFactory activeMQConnectionFactory1(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean("myJms")
    JmsComponent jmsComponent(ConnectionFactory connectionFactory){
        return JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
    }


}
