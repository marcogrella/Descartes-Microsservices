package com.microservices.descartes.model;

public enum RouteStatus {

    STARTED("STARTED"),
    NOT_STARTED("NOT_STARTED"),
    DONE("DONE");

    public String status;

    RouteStatus(String status){
        this.status = status;
    }


}
