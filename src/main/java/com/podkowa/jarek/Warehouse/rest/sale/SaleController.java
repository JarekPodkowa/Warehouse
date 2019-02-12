package com.podkowa.jarek.Warehouse.rest.sale;

import com.podkowa.jarek.Warehouse.db.domain.Sale;
import com.podkowa.jarek.Warehouse.db.domain.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/add")
    public void add(@RequestBody SaleDto saleDto) {
        saleService.add(saleDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        saleService.delete(id);
    }

    @GetMapping("/{id}")
    public Sale get(@PathVariable int id) { return saleService.getById(id);}
}

