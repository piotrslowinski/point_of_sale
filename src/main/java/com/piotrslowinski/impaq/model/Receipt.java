package com.piotrslowinski.impaq.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {


    private List<ReceiptLine> products = new LinkedList<>();

    private BigDecimal totalPrice;

    public List<ReceiptLine> getProducts() {
        return products;
    }

    public BigDecimal calculateTotalPrice() {
        return totalPrice; //TODO
    }

    public void addReceiptLine(String name, BigDecimal price) {
        ReceiptLine receiptLine = new ReceiptLine(name, price);
        products.add(receiptLine);
    }

    public class ReceiptLine {

        private String name;

        private BigDecimal unitPrice;

        public ReceiptLine(String name, BigDecimal unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }
    }
}
