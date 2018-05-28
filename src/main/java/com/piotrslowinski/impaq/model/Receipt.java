package com.piotrslowinski.impaq.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Receipt {


    private List<ReceiptLine> products = new LinkedList<>();

    private BigDecimal totalPrice;

    private class ReceiptLine {

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
