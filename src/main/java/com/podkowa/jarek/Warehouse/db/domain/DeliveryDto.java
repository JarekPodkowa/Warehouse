package com.podkowa.jarek.Warehouse.db.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class DeliveryDto {

    private int id;
    private Product product;
    private Instant recordCreatedOn;
}
