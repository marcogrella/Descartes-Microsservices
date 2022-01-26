package com.microservices.descartes.mapper;


import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class RouteMapper {

    public static final RouteMapper INTANCE = Mappers.getMapper(RouteMapper.class);
    public abstract Route toRouteFromRouteRDTO(RouteRequestDTO routeRequestDTO);
    public abstract RouteResponseDTO toRouteResponse(Route route);
    public abstract Route toRouteFromRoutePDTO(RoutePutDTO routeRequestDTO);


}
