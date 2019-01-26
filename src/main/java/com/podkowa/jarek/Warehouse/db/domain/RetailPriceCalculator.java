package com.podkowa.jarek.Warehouse.db.domain;

import java.math.BigDecimal;

public class RetailPriceCalculator extends PriceCalculator {

    private static final BigDecimal PROFIT_MARGIN = new BigDecimal("0.30");

    @Override
    public BigDecimal calculatePrice(BigDecimal price) {
        if (price.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Retail price can't be 0!");
        } else {
            BigDecimal profit = price.multiply(PROFIT_MARGIN);
            return price.add(profit); // it calculates price of item when it's sold as retail product
        }
    }
}
