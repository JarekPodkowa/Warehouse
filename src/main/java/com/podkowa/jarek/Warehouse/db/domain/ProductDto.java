package com.podkowa.jarek.Warehouse.db.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String code;
    private String name;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private int quantity;
    private Instant recordCreatedOn;

}