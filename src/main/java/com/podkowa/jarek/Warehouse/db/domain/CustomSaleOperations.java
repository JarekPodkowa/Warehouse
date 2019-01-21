package com.podkowa.jarek.Warehouse.db.domain;

public interface CustomSaleOperations {

    void add(SaleDto saleDto);

    void delete(int id);

    Sale getById(int id);
}
