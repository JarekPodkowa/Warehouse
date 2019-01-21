package com.podkowa.jarek.Warehouse.rest.delivery;

public class AddDeliveryException extends RuntimeException {

    public AddDeliveryException(String msg) { super(msg);}

    public AddDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }
}

