package com.podkowa.jarek.Warehouse.db.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, Integer>, CustomDeliveryOperations {


    DeliveryDto findById(int id);
}
