package com.myapp.camel.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class OrderLineItems implements Serializable {

    private String orderId;

    private  List<OrderItem> items = new ArrayList<>();

    private Double total;



    public OrderLineItems(){

    }
    public OrderLineItems(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }
    public Double getTotal(){
        return items.stream().mapToDouble(i -> i.price()*i.quantity()).sum();
    }


}
