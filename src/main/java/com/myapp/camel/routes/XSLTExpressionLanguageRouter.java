package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class XSLTExpressionLanguageRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/ecomorders?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("xslt-expression-language-route")

                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")

                .to("xslt:xslt/transform.xsl")
                .unmarshal().jacksonXml(Map.class)
                .marshal().json(JsonLibrary.Jackson)
                .to("myJms:queue:namecityjson");

    }
}


// SimpleRegistry
// ApplicationContextRegistry
// OsgiServiceRegistry
// CdiBeanRegistry

// Data Formats provided with camel

// XML
// JSON
// CSV
// Bindy
// Avro -> Binary Format
// ProtoBuf
// Gzip
// JAXB
// Crypto
// HL7

