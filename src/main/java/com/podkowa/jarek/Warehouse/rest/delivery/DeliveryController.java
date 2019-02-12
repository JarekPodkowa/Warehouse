package com.podkowa.jarek.Warehouse.rest.delivery;

import com.podkowa.jarek.Warehouse.db.domain.Delivery;
import com.podkowa.jarek.Warehouse.db.domain.DeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryService service;

    @Autowired
    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public void add(@RequestBody DeliveryDto deliveryDto) {
        service.add(deliveryDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Delivery get(@PathVariable int id) {
        return service.getById(id);
    }
}
