package com.podkowa.jarek.Warehouse.rest.product

import com.podkowa.jarek.Warehouse.db.domain.Product
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

    ProductJpaRepository repository = Mock(ProductJpaRepository.class)
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

    def 'should throw delete product exception if delete product failed'() {
        when:
            productService.delete(productDto.getId())
        then:
            1 * repository.delete(productDto.getId()) >> {
                throw new RuntimeException("Delete product with id:" + productDto.getId() + " failed")
            }
            thrown(DeleteProductException)
    }

    def 'should calculate net price for wholesale'() {
        given:
            def netPrice = productDto.getNetPrice()
            def profit = netPrice.multiply(new BigDecimal("0.05"))
            def expectedNetPrice = netPrice.add(profit)
        when:
            Product fetchedProduct = productService.getWholesale(productDto.getId())
        then:
            1 * repository.findByIdIn(productDto.getId()) >> productDto
            expectedNetPrice == fetchedProduct.getNetPrice()
    }

    def 'should calculate net price for retail'() {
        given:
            def netPrice = productDto.getNetPrice()
            def profit = netPrice.multiply(new BigDecimal("0.30"))
            def expectedNetPrice = netPrice.add(profit)
        when:
            Product fetchedProduct = productService.getRetail(productDto.getId())
        then:
            1 * repository.findByIdIn(productDto.getId()) >> productDto
            expectedNetPrice == fetchedProduct.getNetPrice()
    }

    def 'should throw illegal argument exception if price is equal to 0 while calculating net price for wholesale'() {
        given:
            ProductDto invalidProduct = ProductDto.builder()
                    .id(2)
                    .code("code")
                    .name("name")
                    .netPrice(BigDecimal.ZERO)
                    .grossPrice(new BigDecimal("230"))
                    .quantity(3)
                    .recordCreatedOn(Instant.now())
                    .build()
        when:
            productService.getWholesale(invalidProduct.getId())
        then:
            1 * repository.findByIdIn(invalidProduct.getId()) >> invalidProduct
            IllegalArgumentException exception = thrown()
            exception.message == "Wholesale price can't be 0!"
    }

    def 'should throw illegal argument exception if price is equal to 0 while calculating net price for retail'() {
        given:
            ProductDto invalidProduct = ProductDto.builder()
                    .id(2)
                    .code("code")
                    .name("name")
                    .netPrice(BigDecimal.ZERO)
                    .grossPrice(new BigDecimal("230"))
                    .quantity(3)
                    .recordCreatedOn(Instant.now())
                    .build()
        when:
            productService.getRetail(invalidProduct.getId())
        then:
            1 * repository.findByIdIn(invalidProduct.getId()) >> invalidProduct
            IllegalArgumentException exception = thrown()
            exception.message == "Retail price can't be 0!"
    }

    def 'should calculate gross price for wholesale'() {
        given:
            def netPrice = productDto.getGrossPrice()
            def profit = netPrice.multiply(new BigDecimal("0.05"))
            def netPriceWithProfitMargin = netPrice.add(profit)
            def expectedNetPrice = netPriceWithProfitMargin.add(netPriceWithProfitMargin.multiply(new BigDecimal("0.23")))
        when:
            Product fetchedProduct = productService.getWholesale(productDto.getId())
        then:
            1 * repository.findByIdIn(productDto.getId()) >> productDto
            expectedNetPrice == fetchedProduct.getGrossPrice()
    }

    def 'should calculate gross price for retail'() {
        given:
            def netPrice = productDto.getGrossPrice()
            def profit = netPrice.multiply(new BigDecimal("0.30"))
            def netPriceWithProfitMargin = netPrice.add(profit)
            def expectedNetPrice = netPriceWithProfitMargin.add(netPriceWithProfitMargin.multiply(new BigDecimal("0.23")))
        when:
            Product fetchedProduct = productService.getRetail(productDto.getId())
        then:
            1 * repository.findByIdIn(productDto.getId()) >> productDto
            expectedNetPrice == fetchedProduct.getGrossPrice()
    }

    def 'should throw illegal argument exception if price is equal to 0 while calculating gross price for wholesale'() {
        given:
            ProductDto invalidProduct = ProductDto.builder()
                    .id(2)
                    .code("code")
                    .name("name")
                    .netPrice(new BigDecimal("230"))
                    .grossPrice(BigDecimal.ZERO)
                    .quantity(2)
                    .recordCreatedOn(Instant.now())
                    .build()
        when:
            productService.getWholesale(invalidProduct.getId())
        then:
            1 * repository.findByIdIn(invalidProduct.getId()) >> invalidProduct
            IllegalArgumentException exception = thrown()
            exception.message == "Wholesale price can't be 0!"
    }

    def 'should throw illegal argument exception if price is equal to 0 while calculating gross price for retail'() {
        given:
            ProductDto invalidProduct = ProductDto.builder()
                    .id(2)
                    .code("code")
                    .name("name")
                    .netPrice(new BigDecimal("230"))
                    .grossPrice(BigDecimal.ZERO)
                    .quantity(2)
                    .recordCreatedOn(Instant.now())
                    .build()
        when:
            productService.getRetail(invalidProduct.getId())
        then:
            1 * repository.findByIdIn(invalidProduct.getId()) >> invalidProduct
            IllegalArgumentException exception = thrown()
            exception.message == "Retail price can't be 0!"
    }
}
