package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileExpressionLanguageRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/orders?include=.*\\.(xml|json|csv)&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("file-expression-language-route")
                .setHeader("fileType",simple("${file:ext}"))
                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")
                .choice()
                .when(header("fileType").isEqualTo("xml"))
                .to("myJms:queue:xmlproducts")
                .when(header("fileType").isEqualTo("json"))
                .to("myJms:queue:jsonproducts")
                .otherwise()
                .to("myJms:queue:otherproducts")
                        .end();

    }
}

// Spring Integration

// Messaging Channels

// Message Channel from().to()

// Point To Point Channel from("myJms:queue:avroqueue").bean("","")

// Queue (PTP) & Topic (Publish Subscribe)
// from("myJms:topic:avrotopic").to("","")

// GOF

// Messaging Channels (Messaging Channels,PointToPointChannel,PublishScubscribeChannel)

// Message Construction (Message, CommandMessage, DocumentMessage,EventMessage)

// from(


// Message Transformation
// Message Translator
// ContentEnricher
// Content Filter
// XSL Transformations
// ContentBasedRouter choice() when()
// Aggregator
// Splitter
// RoutingSlip
// DynamicRouter

// LoadBalancer
// ErrorHandlingPatterns