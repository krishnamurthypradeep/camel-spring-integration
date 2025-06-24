package com.myapp.camel.transform;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("transformProcessor")
public class TransformProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String enriched= exchange.getMessage().getHeader("myData",String.class);
        String original = exchange.getIn().getBody(String.class);
        exchange.getMessage().setBody(original+" | tranformed with "+enriched);

    }
}
