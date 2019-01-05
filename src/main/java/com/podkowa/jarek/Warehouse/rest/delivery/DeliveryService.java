package com.podkowa.jarek.Warehouse.rest.delivery;

import com.podkowa.jarek.Warehouse.db.domain.DeliveryDto;
import com.podkowa.jarek.Warehouse.db.domain.DeliveryJpaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DeliveryService {

    private DeliveryJpaRepository repository;

    @Autowired
    public DeliveryService(DeliveryJpaRepository repository) {
        this.repository = repository;
    }

    public void add(DeliveryDto deliveryDto) {
        try {
            repository.insertIntoDelivery(deliveryDto);
            log.info("Delivery with id: " + deliveryDto.getId() + "has been added!");
        } catch (Exception ex) {
            throw new RuntimeException("Could not add delivery with id: " + deliveryDto.getId());
        }
    }

    public void delete(int id) {
        try {
            repository.deleteFromDelivery(id);
            log.info("Delivery with id: " + id + " has been deleted!");
        } catch (Exception ex) {
            throw new RuntimeException("Could not delete delivery with id: " + id);
        }
    }
}
