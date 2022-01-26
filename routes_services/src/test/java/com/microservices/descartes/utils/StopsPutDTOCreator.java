package com.microservices.descartes.utils;

import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.model.StopStatus;
import jdk.jshell.Snippet;

public class StopsPutDTOCreator {

    public static StopsPutDTO createStopsPDTO(Long id, String description, String address,
                                                     Double latitude, Double longitude, StopStatus status,
                                                     Integer deliveryRadius){

        return StopsPutDTO.builder()
                .id(id)
                .description(description)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .status(status)
                .deliveryRadius(deliveryRadius)
                .build();
    }



}
