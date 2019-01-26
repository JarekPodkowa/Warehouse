package com.podkowa.jarek.Warehouse.db.domain

import spock.lang.Shared
import spock.lang.Specification

class RetailPriceCalculatorSpec extends Specification {

    @Shared
    PriceCalculator calculator = new RetailPriceCalculator()

    def 'should calculate retail price'() {
        given:
            def price = 305.67
            def expectedRetailPrice = price.add(price * 0.3)
        when:
            def result = calculator.calculatePrice(price)
        then:
            expectedRetailPrice == result
    }

    def 'should throw illegal argument exception if price is equal to 0 while calculating price for retail'() {
        given:
            def price = BigDecimal.ZERO
        when:
            calculator.calculatePrice(price)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == "Retail price can't be 0!"
    }
}
