package com.podkowa.jarek.Warehouse.db.domain;

import lombok.Value;

import java.time.Instant;

@Value
public class SaleDto {

    private int id;
    private Product product;
    private Instant recordCreatedOn;
}
