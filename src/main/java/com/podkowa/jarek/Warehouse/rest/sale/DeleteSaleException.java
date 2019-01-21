package com.podkowa.jarek.Warehouse.rest.sale;

public class DeleteSaleException extends RuntimeException {

    public DeleteSaleException(String message) {
        super(message);
    }

    public DeleteSaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
