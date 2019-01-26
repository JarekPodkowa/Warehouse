package com.podkowa.jarek.Warehouse.db.domain;

import java.math.BigDecimal;

public class PriceCalculator {

    private final static BigDecimal VAT = new BigDecimal("0.23");

    public BigDecimal calculatePrice(BigDecimal price) {
        if (price.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Price can't be 0!");
        } else {
            BigDecimal vatPrice = price.multiply(VAT);
            return price.add(vatPrice); // it calculates price of item by adding vat to it
        }
    }

}
