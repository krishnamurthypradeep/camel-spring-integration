package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FirstRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data/input?noop=true&recursive=true")
                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")
                .to("file:data/output?noop=true");
    }
}
