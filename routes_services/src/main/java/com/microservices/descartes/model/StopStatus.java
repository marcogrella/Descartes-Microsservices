package com.microservices.descartes.model;

public enum StopStatus {

    NOT_ANSWER("NOT_ANSWER"),
    ANSWER("ANSWER");

    public String status;

    StopStatus(String status){
        this.status = status;
    }

}
