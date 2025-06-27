package com.myapp.camel.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("orderRoutingSlipBeanUsingDB")
public class OrderRoutingSlipBeanUsingDB  implements Processor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
       OrderItem orderItem =  exchange.getIn().getBody(OrderItem.class);
       String productName = orderItem.productName();
       String sql = "SELECT route_chain FROM order_routes WHERE product_name=?";
      String route = jdbcTemplate.queryForObject(sql,new Object[]{productName}, String.class);
    if(route == null){
        route = "myJms:queue:otherorders";
    }
    exchange.getIn().setHeader("orderRoutingSlip",route);
    }
}
