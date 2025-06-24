package com.myapp.camel.bean;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
@Data
public class Product {

    private String name;
    private String brand;
    private String description;
    private Double price;
}
