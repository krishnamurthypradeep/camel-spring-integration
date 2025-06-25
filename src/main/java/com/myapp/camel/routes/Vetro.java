package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
// ExpressionLanguages
// camel-core
// Simple
// File (File Related Headers ${file:ext}
// starter kits
// JSONPath (Navigate JSON documents)
// XPath evaluate xpath on xml
// XQuery


// EndPoints
// Components
// File
// Ftp
// SQL (Jdbc | Jpa)
// ExpressionLanguage
// Enterprise Integration Pattern

public class Vetro extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/newinput?noop=true&initialDelay=2000&idempotent=false&delay=5000&include=.*\\.txt$")
                .routeId("vetro-route-1")
                .process("validationProcessor")
                //.process("")
                .to("direct:enrich")
                .process("transformProcessor")
                .choice()
                .when(simple("${body} contains 'mydata'"))
                .log("Routed to queue")
                .to("myJms:queue:myqueue")
                .otherwise()
                .log("Routed to otherqueue")
                .to("myJms:queue:otherqueue")
                .end();
        from("direct:enrich")
                .routeId("vetro-route-2")
                .process("enrichProcessor");

    }
}
