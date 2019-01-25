package com.podkowa.jarek.Warehouse.db.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@AllArgsConstructor
public class DeliveryDto {

    private int id;
    private Product product;
    private Instant recordCreatedOn;
}
