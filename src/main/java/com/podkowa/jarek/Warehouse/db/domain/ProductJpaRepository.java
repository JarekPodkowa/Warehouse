package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Integer>, CustomProductOperations {

    ProductDto findByIdIn(Integer id);
}
