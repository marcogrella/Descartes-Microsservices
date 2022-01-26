package com.microservices.descartes.mapper;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO.RouteResponseDTOBuilder;
import com.microservices.descartes.dto.responsedto.StopResponseDTO;
import com.microservices.descartes.dto.responsedto.StopResponseDTO.StopResponseDTOBuilder;
import com.microservices.descartes.model.Route;
import com.microservices.descartes.model.Route.RouteBuilder;
import com.microservices.descartes.model.Stops;
import com.microservices.descartes.model.Stops.StopsBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-25T23:58:34-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class RouteMapperImpl extends RouteMapper {

    @Override
    public Route toRouteFromRouteRDTO(RouteRequestDTO routeRequestDTO) {
        if ( routeRequestDTO == null ) {
            return null;
        }

        RouteBuilder route = Route.builder();

        route.status( routeRequestDTO.getStatus() );
        route.stops( stopsRequestDTOListToStopsList( routeRequestDTO.getStops() ) );

        return route.build();
    }

    @Override
    public RouteResponseDTO toRouteResponse(Route route) {
        if ( route == null ) {
            return null;
        }

        RouteResponseDTOBuilder routeResponseDTO = RouteResponseDTO.builder();

        routeResponseDTO.id( route.getId() );
        routeResponseDTO.date( route.getDate() );
        routeResponseDTO.status( route.getStatus() );
        routeResponseDTO.stops( stopsListToStopResponseDTOList( route.getStops() ) );

        return routeResponseDTO.build();
    }

    @Override
    public Route toRouteFromRoutePDTO(RoutePutDTO routeRequestDTO) {
        if ( routeRequestDTO == null ) {
            return null;
        }

        RouteBuilder route = Route.builder();

        route.id( routeRequestDTO.getId() );
        route.date( routeRequestDTO.getDate() );
        route.status( routeRequestDTO.getStatus() );
        route.stops( stopsPutDTOListToStopsList( routeRequestDTO.getStops() ) );

        return route.build();
    }

    protected Stops stopsRequestDTOToStops(StopsRequestDTO stopsRequestDTO) {
        if ( stopsRequestDTO == null ) {
            return null;
        }

        StopsBuilder stops = Stops.builder();

        stops.description( stopsRequestDTO.getDescription() );
        stops.address( stopsRequestDTO.getAddress() );
        stops.latitude( stopsRequestDTO.getLatitude() );
        stops.longitude( stopsRequestDTO.getLongitude() );
        stops.deliveryRadius( stopsRequestDTO.getDeliveryRadius() );
        stops.status( stopsRequestDTO.getStatus() );

        return stops.build();
    }

    protected List<Stops> stopsRequestDTOListToStopsList(List<StopsRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Stops> list1 = new ArrayList<Stops>( list.size() );
        for ( StopsRequestDTO stopsRequestDTO : list ) {
            list1.add( stopsRequestDTOToStops( stopsRequestDTO ) );
        }

        return list1;
    }

    protected StopResponseDTO stopsToStopResponseDTO(Stops stops) {
        if ( stops == null ) {
            return null;
        }

        StopResponseDTOBuilder stopResponseDTO = StopResponseDTO.builder();

        stopResponseDTO.id( stops.getId() );
        stopResponseDTO.description( stops.getDescription() );
        stopResponseDTO.address( stops.getAddress() );
        stopResponseDTO.latitude( stops.getLatitude() );
        stopResponseDTO.longitude( stops.getLongitude() );
        stopResponseDTO.deliveryRadius( stops.getDeliveryRadius() );
        stopResponseDTO.status( stops.getStatus() );

        return stopResponseDTO.build();
    }

    protected List<StopResponseDTO> stopsListToStopResponseDTOList(List<Stops> list) {
        if ( list == null ) {
            return null;
        }

        List<StopResponseDTO> list1 = new ArrayList<StopResponseDTO>( list.size() );
        for ( Stops stops : list ) {
            list1.add( stopsToStopResponseDTO( stops ) );
        }

        return list1;
    }

    protected Stops stopsPutDTOToStops(StopsPutDTO stopsPutDTO) {
        if ( stopsPutDTO == null ) {
            return null;
        }

        StopsBuilder stops = Stops.builder();

        stops.id( stopsPutDTO.getId() );
        stops.description( stopsPutDTO.getDescription() );
        stops.address( stopsPutDTO.getAddress() );
        stops.latitude( stopsPutDTO.getLatitude() );
        stops.longitude( stopsPutDTO.getLongitude() );
        stops.deliveryRadius( stopsPutDTO.getDeliveryRadius() );
        stops.status( stopsPutDTO.getStatus() );

        return stops.build();
    }

    protected List<Stops> stopsPutDTOListToStopsList(List<StopsPutDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Stops> list1 = new ArrayList<Stops>( list.size() );
        for ( StopsPutDTO stopsPutDTO : list ) {
            list1.add( stopsPutDTOToStops( stopsPutDTO ) );
        }

        return list1;
    }
}
