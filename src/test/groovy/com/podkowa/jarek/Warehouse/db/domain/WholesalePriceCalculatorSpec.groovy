package com.podkowa.jarek.Warehouse.db.domain

import spock.lang.Shared
import spock.lang.Specification

class WholesalePriceCalculatorSpec extends Specification {

    @Shared
    PriceCalculator calculator = new WholesalePriceCalculator()

    def 'should calculate retail price for wholesale'() {
        given:
            def price = 305.67
            def expectedRetailPrice = price.add(price * 0.05)
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
            exception.message == "Wholesale price can't be 0!"
    }
}
