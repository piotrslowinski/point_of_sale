package com.piotrslowinski.impaq.model;

import java.math.BigDecimal;

public class Product {

    private String barCode;
    private String name;
    private BigDecimal price;

    public Product(String barCode, String name, BigDecimal price) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2);
    }
}
