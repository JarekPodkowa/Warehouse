package com.podkowa.jarek.Warehouse.db.domain.facade;

import com.podkowa.jarek.Warehouse.db.domain.factory.DeliveryFactory;
import com.podkowa.jarek.Warehouse.db.domain.factory.ProductFactory;
import com.podkowa.jarek.Warehouse.db.domain.factory.SaleFactory;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseFactoryFacade {

    @Delegate
    private final DeliveryFactory deliveryFactory;

    @Delegate
    private final ProductFactory productFactory;

    @Delegate
    private final SaleFactory saleFactory;

    @Autowired
    public WarehouseFactoryFacade(DeliveryFactory deliveryFactory, ProductFactory productFactory, SaleFactory saleFactory) {
        this.deliveryFactory = deliveryFactory;
        this.productFactory = productFactory;
        this.saleFactory = saleFactory;
    }
}
