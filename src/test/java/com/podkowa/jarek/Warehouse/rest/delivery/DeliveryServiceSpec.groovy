package com.podkowa.jarek.Warehouse.rest.delivery

import com.podkowa.jarek.Warehouse.db.domain.DeliveryDto
import com.podkowa.jarek.Warehouse.db.domain.DeliveryJpaRepository
import com.podkowa.jarek.Warehouse.db.domain.Product
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

class DeliveryServiceSpec extends Specification {

    DeliveryJpaRepository repository = Mock()
    DeliveryService deliveryService = new DeliveryService(repository)

    @Shared
    def deliveryDto = DeliveryDto.builder()
            .id(1)
            .product(Product.builder()
            .code("SAM")
            .name("Samsung S1")
            .netPrice(new BigDecimal("100"))
            .grossPrice(new BigDecimal("123"))
            .quantity(1)
            .build())
            .recordCreatedOn(Instant.now())
            .build()

    def 'should add new delivery'() {
        when:
            deliveryService.add(deliveryDto)
        then:
            1 * repository.insertIntoDelivery(deliveryDto)
            0 * _
    }

    def 'should throw add delivery exception if adding new delivery failed'() {
        when:
            deliveryService.add(deliveryDto)
        then:
            1 * repository.insertIntoDelivery(deliveryDto) >> { throw new Exception("Example add exception") }
            thrown(AddDeliveryException)
    }

    def 'should delete delivery'() {
        when:
            deliveryService.delete(deliveryDto.getId())
        then:
            1 * repository.deleteFromDelivery(deliveryDto.getId())
            0 * _
    }

    def 'should throw delete delivery exception if delete delivery failed'() {
        when:
            deliveryService.delete(deliveryDto.getId())
        then:
            1 * repository.deleteFromDelivery(deliveryDto.getId()) >> {
                throw new Exception("Example delete exception")
            }
            thrown(DeleteDeliveryException)
    }
}
