package com.microservices.descartes.utils;

import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;
import com.microservices.descartes.validation.NonZeroValues;

import javax.validation.constraints.*;
import java.util.List;

public class RouteRequestDTOCreator {

    public static RouteRequestDTO createRouteRDTO(RouteStatus status, List<StopsRequestDTO> stops){
        return RouteRequestDTO.builder()
                .status(status)
                .stops(stops)
                .build();

    }


}
