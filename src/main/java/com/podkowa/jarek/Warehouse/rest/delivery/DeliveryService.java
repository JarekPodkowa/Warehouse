package com.podkowa.jarek.Warehouse.rest.delivery;

import com.podkowa.jarek.Warehouse.db.domain.CustomDeliveryOperations;
import com.podkowa.jarek.Warehouse.db.domain.Delivery;
import com.podkowa.jarek.Warehouse.db.domain.DeliveryDto;
import com.podkowa.jarek.Warehouse.db.domain.DeliveryJpaRepository;
import com.podkowa.jarek.Warehouse.db.domain.facade.WarehouseFactoryFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DeliveryService implements CustomDeliveryOperations {

    private DeliveryJpaRepository repository;
    private WarehouseFactoryFacade warehouseFactoryFacade;

    @Autowired
    public DeliveryService(DeliveryJpaRepository repository, WarehouseFactoryFacade warehouseFactoryFacade) {
        this.repository = repository;
        this.warehouseFactoryFacade = warehouseFactoryFacade;
    }

    public void add(DeliveryDto deliveryDto) {
        try {
            repository.add(deliveryDto);
            log.info("Delivery with id: " + deliveryDto.getId() + "has been added!");
        } catch (Exception ex) {
            throw new AddDeliveryException("Could not add delivery with id: " + deliveryDto.getId());
        }
    }

    public void delete(int id) {
        try {
            repository.delete(id);
            log.info("Delivery with id: " + id + " has been deleted!");
        } catch (Exception ex) {
            throw new DeleteDeliveryException("Could not delete delivery with id: " + id);
        }
    }

    public Delivery getById(int id) {
        return warehouseFactoryFacade.toDelivery(repository.findById(id));
    }
}
