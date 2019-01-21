package com.podkowa.jarek.Warehouse.rest.delivery;

public class DeleteDeliveryException extends RuntimeException {

    public DeleteDeliveryException(String message) {
        super(message);
    }

    public DeleteDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }
}
