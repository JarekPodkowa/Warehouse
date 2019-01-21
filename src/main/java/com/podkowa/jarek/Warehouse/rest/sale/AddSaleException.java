package com.podkowa.jarek.Warehouse.rest.sale;

public class AddSaleException extends RuntimeException {

    public AddSaleException(String message) {
        super(message);
    }

    public AddSaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
