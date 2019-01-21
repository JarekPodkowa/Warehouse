package com.podkowa.jarek.Warehouse.db.domain;

public interface CustomProductOperations {

    void add(ProductDto productDto);

    void delete(int id);

    Product getById(int id);
}
