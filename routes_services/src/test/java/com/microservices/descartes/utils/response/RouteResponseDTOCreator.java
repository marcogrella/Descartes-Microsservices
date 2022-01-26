package com.microservices.descartes.utils.response;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.dto.responsedto.StopResponseDTO;
import com.microservices.descartes.model.RouteStatus;

import java.time.OffsetDateTime;
import java.util.List;

public class RouteResponseDTOCreator {

    public static RouteResponseDTO createRRDTO(Long id, OffsetDateTime date, RouteStatus status,
                                               List<StopResponseDTO> stops){
        return RouteResponseDTO.builder()
                .id(id)
                .date(date)
                .status(status)
                .stops(stops)
                .build();

    }

}
