package com.microservices.descartes.exceptions;

public class RouteNotFoundException extends Exception {
    public RouteNotFoundException(String message) {
        super(message);
    }
}
