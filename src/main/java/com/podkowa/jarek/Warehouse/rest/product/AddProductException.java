package com.podkowa.jarek.Warehouse.rest.product;

public class AddProductException extends RuntimeException {

    public AddProductException(String message) {
        super(message);
    }

    public AddProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
