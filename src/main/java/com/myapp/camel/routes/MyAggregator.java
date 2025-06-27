package com.myapp.camel.routes;

import com.myapp.camel.bean.OrderItem;
import com.myapp.camel.bean.OrderLineItems;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;


public class MyAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        OrderItem newItem = newExchange.getIn().getBody(OrderItem.class);

        if (newItem == null) {
            throw new IllegalStateException("OrderItem is null in newExchange");
        }

        OrderLineItems aggregated;

        if (oldExchange == null) {
            System.out.println("New Exchange: " + newExchange);
            aggregated = new OrderLineItems(newItem.orderId());
            aggregated.addItem(newItem);
            newExchange.getMessage().setBody(aggregated);
            return newExchange;
        } else {
            System.out.println("Old Exchange: " + oldExchange);
            aggregated = oldExchange.getMessage().getBody(OrderLineItems.class);

            if (aggregated == null) {
                aggregated = new OrderLineItems(newItem.orderId());
            }

            aggregated.addItem(newItem);
            oldExchange.getMessage().setBody(aggregated);
            return oldExchange;
        }
    }
}

