package com.podkowa.jarek.Warehouse.db.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class SaleDto {

    private int id;
    private Product product;
    private Instant recordCreatedOn;
}
