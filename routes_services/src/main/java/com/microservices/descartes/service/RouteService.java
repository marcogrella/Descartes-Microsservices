package com.microservices.descartes.service;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.exceptions.RouteNotFoundException;
import com.microservices.descartes.exceptions.StopsNotFoundException;
import com.microservices.descartes.mapper.RouteMapper;
import com.microservices.descartes.model.Route;
import com.microservices.descartes.repository.RouteRepository;
import com.microservices.descartes.repository.StopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class RouteService {

    @Autowired
    private final RouteRepository repository;

    @Autowired
    private final StopsRepository stopsRepository;

    @Transactional(rollbackFor = Exception.class)
    public RouteResponseDTO save(RouteRequestDTO routeRequestDTO) {
        Route routeSaved = repository.save(RouteMapper.INTANCE.toRouteFromRouteRDTO(routeRequestDTO));
        RouteResponseDTO responseDTO = RouteMapper.INTANCE.toRouteResponse(routeSaved);
        return responseDTO;
    }

    public RouteResponseDTO findById(Long id) throws RouteNotFoundException {
        Route route = findByIdOrThrowRouteNotFoundException(id);
        return RouteMapper.INTANCE.toRouteResponse(route);

    }

    public void replace(RoutePutDTO routePutDTO) throws RouteNotFoundException, StopsNotFoundException {
        verifiesIfIdExistsorThrowRouteNotFoundException(routePutDTO.getId());

        for (StopsPutDTO stops : routePutDTO.getStops()) {
            verifiesIfStopsExistsOrThrowStopsNotFoundException(stops.getId());
        }

        Route route = RouteMapper.INTANCE.toRouteFromRoutePDTO(routePutDTO);
        repository.save(route);
    }

    public void deleteById(Long id) throws RouteNotFoundException {
        repository.delete(findByIdOrThrowRouteNotFoundException(id));
    }


    public Route findByIdOrThrowRouteNotFoundException(Long id) throws RouteNotFoundException {
           return  repository.findById(id).orElseThrow(() ->
                   new RouteNotFoundException("Route with " + id + " not found "));
    }


    public void verifiesIfIdExistsorThrowRouteNotFoundException(Long id) throws RouteNotFoundException {
        repository.findById(id).orElseThrow(() ->
                new RouteNotFoundException("Route with " + id + " not found "));
    }


    public void verifiesIfStopsExistsOrThrowStopsNotFoundException(Long id) throws StopsNotFoundException {
        stopsRepository.findById(id).orElseThrow(() ->
                                new StopsNotFoundException("Stops with " + id + " not found "));

    }

}



