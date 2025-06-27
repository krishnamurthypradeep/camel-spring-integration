package com.myapp.camel.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Body;
import org.apache.camel.language.bean.Bean;

import java.util.Map;

public class OrderItemConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderItem mapToOrderItem1(@Body OrderItem orderItem) {
        return orderItem;
    }

    public OrderItem mapToOrderItem(Map<String, Object> map) {
        return objectMapper.convertValue(map, OrderItem.class);
    }
}
