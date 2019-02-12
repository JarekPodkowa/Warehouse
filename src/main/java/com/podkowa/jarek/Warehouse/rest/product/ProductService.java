package com.podkowa.jarek.Warehouse.rest.product;

import com.podkowa.jarek.Warehouse.db.domain.CustomProductOperations;
import com.podkowa.jarek.Warehouse.db.domain.PriceCalculator;
import com.podkowa.jarek.Warehouse.db.domain.Product;
import com.podkowa.jarek.Warehouse.db.domain.ProductDto;
import com.podkowa.jarek.Warehouse.db.domain.ProductJpaRepository;
import com.podkowa.jarek.Warehouse.db.domain.RetailPriceCalculator;
import com.podkowa.jarek.Warehouse.db.domain.WholesalePriceCalculator;
import com.podkowa.jarek.Warehouse.db.domain.facade.WarehouseFactoryFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProductService implements CustomProductOperations {

    private ProductJpaRepository repository;
    private WarehouseFactoryFacade warehouseFactoryFacade;
    private PriceCalculator wholeSalePriceCalculator;
    private PriceCalculator retailSalePriceCalculator;
    private PriceCalculator vatPriceCalculator;

    @Autowired
    public ProductService(ProductJpaRepository repository, WarehouseFactoryFacade warehouseFactoryFacade) {
        this.repository = repository;
        this.warehouseFactoryFacade = warehouseFactoryFacade;
        this.wholeSalePriceCalculator = new WholesalePriceCalculator();
        this.retailSalePriceCalculator = new RetailPriceCalculator();
        this.vatPriceCalculator = new PriceCalculator();
    }

    public void add(ProductDto productDto) {
        try {
            repository.add(productDto);
            log.info("Product with code: " + productDto.getCode() + "has been added!");
        } catch (Exception ex) {
            throw new AddProductException("Could not add product!");
        }
    }

    public void delete(int productId) {
        try {
            repository.delete(productId);
            log.info("Product with id: " + productId + " has been successfully deleted!");
        } catch (Exception ex) {
            throw new DeleteProductException("Could not delete product with id: " + productId);
        }
    }

    public Product getRetail(int id) {
        ProductDto productDto = repository.findByIdIn(id);
        ProductDto productWithCorrectPrice = ProductDto.builder()
                .id(productDto.getId())
                .code(productDto.getCode())
                .name(productDto.getName())
                .netPrice(retailSalePriceCalculator.calculatePrice(productDto.getNetPrice()))
                .grossPrice(vatPriceCalculator.calculatePrice(
                        retailSalePriceCalculator.calculatePrice(productDto.getGrossPrice()
                        )))
                .quantity(productDto.getQuantity())
                .recordCreatedOn(productDto.getRecordCreatedOn())
                .build();

        return warehouseFactoryFacade.toProduct(productWithCorrectPrice);
    }

    public Product getWholesale(int id) {
        ProductDto productDto = repository.findByIdIn(id);
        ProductDto productWithCorrectPrice = ProductDto.builder()
                .id(productDto.getId())
                .code(productDto.getCode())
                .name(productDto.getName())
                .netPrice(wholeSalePriceCalculator.calculatePrice(productDto.getNetPrice()))
                .grossPrice(vatPriceCalculator.calculatePrice(
                        wholeSalePriceCalculator.calculatePrice(productDto.getGrossPrice())
                ))
                .quantity(productDto.getQuantity())
                .recordCreatedOn(productDto.getRecordCreatedOn())
                .build();

        return warehouseFactoryFacade.toProduct(productWithCorrectPrice);
    }
}
