package com.podkowa.jarek.Warehouse.rest.product;

public class DeleteProductException extends RuntimeException {

    public DeleteProductException(String message) {
        super(message);
    }

    public DeleteProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
