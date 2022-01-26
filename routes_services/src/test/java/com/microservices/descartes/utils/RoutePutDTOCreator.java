package com.microservices.descartes.utils;


import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.model.RouteStatus;

import java.time.OffsetDateTime;
import java.util.List;

public class RoutePutDTOCreator {

    public static RoutePutDTO createPDTO(Long id, RouteStatus status, OffsetDateTime date, List<StopsPutDTO> stops){
        return RoutePutDTO.builder()
                .id(id)
                .status(status)
                .date(date)
                .stops(stops)
                .build();

    }
}
