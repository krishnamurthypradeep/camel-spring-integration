package com.myapp.camel.enrich;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("enrichProcessor")
public class DataEnrichProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getMessage().setHeader("myData","SOME INFO");
    }
}
