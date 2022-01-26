package com.microservices.descartes.repository;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.model.Route;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;
import com.microservices.descartes.model.Stops;
import com.microservices.descartes.utils.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class RouteRepositoryTest {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    StopsRepository stopsRepository;

    @BeforeEach
    void cleanDataBase(){
        routeRepository.deleteAll();
        stopsRepository.deleteAll();
    }

    @Test
    @DisplayName("Test to save a valid route into database")
    void savesARouteWhenSuccessfull() {

        List<StopsRequestDTO> listStops = new ArrayList<>();

        StopsRequestDTO stopsRDTO = StopsRequestCreator.createStopDTO(
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                 -3.751316,
                 -38.4841062,
                StopStatus.ANSWER,
                45);

        listStops.add(stopsRDTO);
        RouteRequestDTO routeRDTO = RouteRequestDTOCreator.createRouteRDTO(RouteStatus.STARTED, listStops);

        Route routeToBeSaved = RouteMapper.INTANCE.toRouteFromRouteRDTO(routeRDTO);
        Route routeSaved = routeRepository.save(routeToBeSaved);

        Assertions.assertThat(routeSaved).isNotNull();
        Assertions.assertThat(routeSaved.getId()).isNotNull();
        Assertions.assertThat(routeSaved.getStops().size()).isEqualTo(1);


    }


    @Test
    @DisplayName("Test to delete a saved route present in database")
    void delete_RemovesARoute_WhenSuccessfull() {

        List<StopsRequestDTO> listStops = new ArrayList<>();

        StopsRequestDTO stopsRDTO = StopsRequestCreator.createStopDTO(
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                StopStatus.ANSWER,
                45);

        StopsRequestDTO stopsRDTO2 = StopsRequestCreator.createStopDTO(
                "Galeto Prime - Delivery iFood",
                "Rua Professor Eládio Magalhães, 213 - Edson Queiroz, Fortaleza - CE, 60811-460",
                -3.711115131,
                -38.4841062,
                StopStatus.ANSWER,
                15);


        listStops.add(stopsRDTO);
        listStops.add(stopsRDTO2);

        RouteRequestDTO routeRDTO = RouteRequestDTOCreator.createRouteRDTO(RouteStatus.STARTED, listStops);

        Route routeToBeSaved = RouteMapper.INTANCE.toRouteFromRouteRDTO(routeRDTO);
        Route routeSaved = routeRepository.save(routeToBeSaved);

        routeRepository.deleteById(routeSaved.getId());
        Optional<Route> routeOptional = routeRepository.findById(routeSaved.getId());
        Assertions.assertThat(routeOptional).isEmpty();

        Optional<Stops> stopsOptional = stopsRepository.findById(routeSaved.getStops().get(0).getId());
        Optional<Stops> stopsOptional2 = stopsRepository.findById(routeSaved.getStops().get(1).getId());
        Assertions.assertThat(stopsOptional.isEmpty());
        Assertions.assertThat(stopsOptional2.isEmpty());


    }




    @Test
    @DisplayName("Test to update a saved route present in database")
    void update_UpdatesARoute_WhenSuccessfull() {

        List<StopsRequestDTO> listStops = new ArrayList<>();

        StopsRequestDTO stopsRDTO = StopsRequestCreator.createStopDTO(
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                StopStatus.ANSWER,
                45);

        listStops.add(stopsRDTO);

        RouteRequestDTO routeRDTO = RouteRequestDTOCreator.createRouteRDTO(RouteStatus.NOT_STARTED, listStops);

        Route routeToBeSaved = RouteMapper.INTANCE.toRouteFromRouteRDTO(routeRDTO);
        Route routeSaved = routeRepository.save(routeToBeSaved);



        // updating a Route and some field at the related list

        StopsPutDTO stopsPDTO = StopsPutDTOCreator.createStopsPDTO(1l, stopsRDTO.getDescription(), stopsRDTO.getAddress(),
                -35.751316, -39.4841062, StopStatus.NOT_ANSWER, 50);

        List<StopsPutDTO> listSPDTO = new ArrayList<>();
        listSPDTO.add(stopsPDTO);


        RoutePutDTO routePDTO = RoutePutDTOCreator.createPDTO(routeSaved.getId(), RouteStatus.STARTED,
                routeSaved.getDate(), listSPDTO);

        Route routeToBeUpdate = RouteMapper.INTANCE.toRouteFromRoutePDTO(routePDTO);
        Route routeUpdated = routeRepository.save(routeToBeUpdate);

        Assertions.assertThat(routeRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(routeSaved.getStatus()).isNotEqualTo(routeUpdated.getStatus());
        Assertions.assertThat(routeSaved.getId()).isEqualTo(routeUpdated.getId());
        Assertions.assertThat(routeSaved.getStops().get(0).getStatus())
                .isNotEqualTo(routeUpdated.getStops().get(0).getStatus());






    }




}