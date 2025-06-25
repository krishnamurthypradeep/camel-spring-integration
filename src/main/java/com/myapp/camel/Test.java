package com.myapp.camel;

import com.myapp.camel.avro.Order;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Order order = Order.newBuilder().setOrderId("A1234")

                .setCity("Bengaluru")
                .setPrice(755467.5)
                .build();
        System.out.println(order);
    }
}
