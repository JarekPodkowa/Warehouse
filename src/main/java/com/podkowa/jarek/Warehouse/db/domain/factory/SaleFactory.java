package com.podkowa.jarek.Warehouse.db.domain.factory;

import com.podkowa.jarek.Warehouse.db.domain.Sale;
import com.podkowa.jarek.Warehouse.db.domain.SaleDto;
import org.springframework.stereotype.Component;

@Component
public class SaleFactory {

    public Sale toSale(SaleDto saleDto) {
        return Sale.builder()
                .id(saleDto.getId())
                .product(saleDto.getProduct())
                .recordCreatedOn(saleDto.getRecordCreatedOn())
                .build();

    }
}
