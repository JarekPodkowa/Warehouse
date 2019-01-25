package com.podkowa.jarek.Warehouse.rest.product

import com.podkowa.jarek.Warehouse.db.domain.ProductDto
import com.podkowa.jarek.Warehouse.db.domain.ProductJpaRepository
import com.podkowa.jarek.Warehouse.db.domain.facade.WarehouseFactoryFacade
import com.podkowa.jarek.Warehouse.db.domain.factory.DeliveryFactory
import com.podkowa.jarek.Warehouse.db.domain.factory.ProductFactory
import com.podkowa.jarek.Warehouse.db.domain.factory.SaleFactory
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

class ProductServiceSpec extends Specification {

    ProductJpaRepository repository = Mock()
    DeliveryFactory deliveryFactory = new DeliveryFactory()
    ProductFactory productFactory = new ProductFactory()
    SaleFactory saleFactory = new SaleFactory()
    WarehouseFactoryFacade facade = new WarehouseFactoryFacade(deliveryFactory, productFactory, saleFactory)
    ProductService productService = new ProductService(repository, facade)

    @Shared
    def productDto = ProductDto.builder()
            .id(1)
            .code("SAM")
            .name("Samsung S2")
            .netPrice(new BigDecimal("120"))
            .grossPrice(new BigDecimal("147.6"))
            .quantity(2)
            .recordCreatedOn(Instant.now())
            .build()

    def 'should add new product'() {
        when:
            productService.add(productDto)
        then:
            1 * repository.add(productDto)
            0 * _
    }

    def 'should throw add product exception if adding new product failed'() {
        when:
            productService.add(productDto)
        then:
            1 * repository.add(productDto) >> { throw new RuntimeException("Adding product failed") }
            thrown(AddProductException)
    }

    def 'should delete product'() {
        when:
            productService.delete(productDto.getId())
        then:
            1 * repository.delete(productDto.getId())
            0 * _
    }

    def 'should throw delete product exception if delelte product failed'() {
        when:
            productService.delete(productDto.getId())
        then:
            1 * repository.delete(productDto.getId()) >> {
                throw new RuntimeException("Delete product with id:" + productDto.getId() + " failed")
            }
            thrown(DeleteProductException)
    }
}
