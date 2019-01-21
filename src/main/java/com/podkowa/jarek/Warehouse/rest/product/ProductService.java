package com.podkowa.jarek.Warehouse.rest.product;

import com.podkowa.jarek.Warehouse.db.domain.ProductDto;
import com.podkowa.jarek.Warehouse.db.domain.ProductJpaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProductService {

    private ProductJpaRepository repository;

    @Autowired
    public ProductService(ProductJpaRepository repository) {
        this.repository = repository;
    }

    public void add(ProductDto productDto) {
        try {
            repository.insertIntoProduct(productDto);
            log.info("Product with code: " + productDto.getCode() + "has been added!");
        } catch (Exception ex) {
            throw new AddProductException("Could not add product!");
        }
    }

    public void delete(int productId) {
        try{
            repository.deleteFromProduct(productId);
            log.info("Product with id: "  + productId + " has been successfully deleted!");
        } catch (Exception ex) {
            throw new DeleteProductException("Could not delete product with id: " + productId);
        }

    }
}
