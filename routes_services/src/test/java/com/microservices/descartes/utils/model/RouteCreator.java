package com.microservices.descartes.utils.model;

import com.microservices.descartes.model.Route;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.Stops;

import java.time.OffsetDateTime;
import java.util.List;

public class RouteCreator {

    public static Route createRoute(Long id,  OffsetDateTime date, RouteStatus status, List<Stops> stops){
        return Route.builder()
                .id(id)
                .date(date)
                .status(status)
                .stops(stops)
                .build();

    }

}
