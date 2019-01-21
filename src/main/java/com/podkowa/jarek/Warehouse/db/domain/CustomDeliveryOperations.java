package com.podkowa.jarek.Warehouse.db.domain;

public interface CustomDeliveryOperations {

    void add(DeliveryDto deliveryDto);

    void delete(int id);

    Delivery getById(int id);
}
