package com.podkowa.jarek.Warehouse.db.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class ProductDto {

    private String code;
    private String name;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private int quantity;

}