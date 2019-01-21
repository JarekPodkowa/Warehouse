package com.podkowa.jarek.Warehouse.db.domain.factory;

import com.podkowa.jarek.Warehouse.db.domain.Product;
import com.podkowa.jarek.Warehouse.db.domain.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .code(productDto.getCode())
                .name(productDto.getName())
                .grossPrice(productDto.getGrossPrice())
                .quantity(productDto.getQuantity())
                .recordCreatedOn(productDto.getCreatedOn())
                .build();
    }
}
