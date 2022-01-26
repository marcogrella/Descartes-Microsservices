package com.microservices.descartes.utils.model;

import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.dto.responsedto.StopResponseDTO;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;
import com.microservices.descartes.model.Stops;

import java.time.OffsetDateTime;
import java.util.List;

public class StopsCreator {

    public static Stops createStops(Long id, String description, String address, Double latitude, Double longitude,
                                    Integer deliveryRadius, StopStatus status){
        return Stops.builder()
                .id(id)
                .description(description)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .deliveryRadius(deliveryRadius)
                .status(status)
                .build();

    }

}
