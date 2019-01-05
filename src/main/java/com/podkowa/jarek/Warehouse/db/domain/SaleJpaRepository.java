package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleJpaRepository extends JpaRepository<Sale, Integer>, CustomSaleOperations {
}
