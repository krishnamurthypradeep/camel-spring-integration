package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class JmsToDBRoute extends RouteBuilder {


//    try{
//
//    }catch(){}
    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("myJms:queue:DeadLetterQueue"));
        from("myJms:queue:neworders")
                .routeId("jms-to-db")
               // .doTry()
                .bean("","")
                .to("sql:insert into orders(id,name,priority) values(:#id,:#name,:#priority)")
               // .endDoTry()
                //.doCatch(SQLException.class)
                .to("myJms:queue:DeadLetterQueue");
    }
}
// DefaultErrorhandler
// No Redelivery
// Exceptions are propogated back to the handler
// StackTrace will be printed to the log
// RoutingHistory is printed to log

// DeadLetterChannel EIP

// TransactionErrorHandler
// NoErrorHandler
// LoggingErrorHandler
