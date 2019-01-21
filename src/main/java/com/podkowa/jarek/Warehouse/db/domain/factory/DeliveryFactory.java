package com.podkowa.jarek.Warehouse.db.domain.factory;

import com.podkowa.jarek.Warehouse.db.domain.Delivery;
import com.podkowa.jarek.Warehouse.db.domain.DeliveryDto;
import org.springframework.stereotype.Component;

@Component
public class DeliveryFactory {

    public Delivery toDelivery(DeliveryDto deliveryDto) {
        return Delivery.builder()
                .id(deliveryDto.getId()) //@TODO to delete because it is handled by database
                .product(deliveryDto.getProduct())
                .recordCreatedOn(deliveryDto.getRecordCreatedOn())
                .build();
    }
}
