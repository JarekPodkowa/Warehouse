package com.podkowa.jarek.Warehouse.db.domain;

public interface CustomProductOperations {

    void insertIntoProduct(ProductDto productDto);

    void deleteFromProduct(int id);
}
