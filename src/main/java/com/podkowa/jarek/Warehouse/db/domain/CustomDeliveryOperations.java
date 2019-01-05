package com.podkowa.jarek.Warehouse.db.domain;

public interface CustomDeliveryOperations {

    void insertIntoDelivery(DeliveryDto deliveryDto);

    void deleteFromDelivery(int id);
}
