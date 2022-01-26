package com.microservices.descartes.utils.response;

import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.dto.responsedto.StopResponseDTO;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;

import java.time.OffsetDateTime;
import java.util.List;

public class StopsResponseDTOCreator {

    public static StopResponseDTO createSRDTO(Long id, String description, String address, Double latitude, Double longitude,
                                              StopStatus status, Integer deliveryRadius){

        return StopResponseDTO.builder()
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
