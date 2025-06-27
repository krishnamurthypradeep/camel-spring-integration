package com.myapp.camel.bean;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.language.bean.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("dynamicRouterBean")
public class DynamicRouterBean {

    public String decideNextHop(@Body OrderItem orderItem,
                              @ExchangeProperties Map<String,Object> props){

      Integer step = (Integer) props.getOrDefault("routingStep",0);
      props.put("routingStep",step+1);

      if(step ==0 && "Widget".equals(orderItem.productName())){
          return "bean:validateWidget";
      }
        if(step ==1 && orderItem.quantity() > 1){
            return "myJms:queue:bulkOrders";
        }
        if(step ==2 ){
            return "myJms:queue:finalOrders";
        }
      return null;
    }
}
