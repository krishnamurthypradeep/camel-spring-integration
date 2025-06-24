package com.myapp.camel.routes;

import com.myapp.camel.bean.Product;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

//@Component
public class FileToActiveMQAndXMLToJSON  extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        JaxbDataFormat format = new JaxbDataFormat();
        format.setContextPath(Product.class.getPackageName());

        from("file:data/xml?include=products-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
                .unmarshal(format)
                .marshal().json()
                 .log(" contents:\n ${bodyAs(String)}").
                to("jms1:queue:productsA","jms1:queue:productsB");
    }
}
// camel-spring-boot-activemq-starter
// JMSComponent ("jms")
// ActiveMQComponent ("activemq")

// 460 Components
