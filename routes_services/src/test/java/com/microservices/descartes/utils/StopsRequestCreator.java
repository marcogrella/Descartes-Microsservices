package com.microservices.descartes.utils;

import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.model.StopStatus;

public class StopsRequestCreator {

    public static StopsRequestDTO createStopDTO(String description, String address,
                                                Double latitude, Double longitude, StopStatus status,
                                                Integer deliveryRadius){
        return StopsRequestDTO.builder()
                .description(description)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .status(status)
                .deliveryRadius(deliveryRadius)
                .build();
    }


    /*






    private Integer deliveryRadius;
 */
}
