package com.podkowa.jarek.Warehouse.rest.sale;

import com.podkowa.jarek.Warehouse.db.domain.CustomSaleOperations;
import com.podkowa.jarek.Warehouse.db.domain.SaleDto;
import com.podkowa.jarek.Warehouse.db.domain.SaleJpaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SaleService implements CustomSaleOperations {

    private SaleJpaRepository repository;

    @Autowired
    public SaleService(SaleJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(SaleDto saleDto) {
        try{
            repository.add(saleDto);
            log.info("Sale with id: " + saleDto.getId() + " has been added!");
        } catch (Exception ex) {
            throw new AddSaleException("Could not ad sale with id: " + saleDto.getId());
        }
    }

    @Override
    public void delete(int id) {
        try{
            repository.delete(id);
            log.info("Sale with id: " + id + "has been deleted!");
        } catch (Exception ex) {
            throw new DeleteSaleException("Could not delete sale with id: " + id);
        }
    }
}
