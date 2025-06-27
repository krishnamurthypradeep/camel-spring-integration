package com.myapp.camel.routes;

import org.apache.avro.Schema;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.avro.AvroDataFormat;
import org.springframework.stereotype.Component;


import java.io.InputStream;

//@Component
public class AvroConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        InputStream schemaStream = getClass().getResourceAsStream("/Order.avsc");
        Schema schema = new Schema.Parser().parse(schemaStream);
        AvroDataFormat avroDataFormat = new AvroDataFormat(schema);

        from("myJms:queue:orderavro")
                .unmarshal(avroDataFormat) // Convert Avro binary to Java object
                .log("✅ Received Order: ${body}");
    }
}
