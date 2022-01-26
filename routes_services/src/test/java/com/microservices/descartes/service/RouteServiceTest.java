package com.microservices.descartes.service;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.requestdto.StopsPutDTO;
import com.microservices.descartes.dto.requestdto.StopsRequestDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.dto.responsedto.StopResponseDTO;
import com.microservices.descartes.exceptions.RouteNotFoundException;
import com.microservices.descartes.exceptions.StopsNotFoundException;
import com.microservices.descartes.mapper.RouteMapper;
import com.microservices.descartes.model.Route;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;
import com.microservices.descartes.model.Stops;
import com.microservices.descartes.repository.RouteRepository;
import com.microservices.descartes.repository.StopsRepository;
import com.microservices.descartes.utils.RoutePutDTOCreator;
import com.microservices.descartes.utils.RouteRequestDTOCreator;
import com.microservices.descartes.utils.StopsPutDTOCreator;
import com.microservices.descartes.utils.StopsRequestCreator;
import com.microservices.descartes.utils.model.RouteCreator;
import com.microservices.descartes.utils.model.StopsCreator;
import com.microservices.descartes.utils.response.RouteResponseDTOCreator;
import com.microservices.descartes.utils.response.StopsResponseDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)

class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private StopsRepository stopsRepository;

    @InjectMocks
    private RouteService serviceTest;


    @Test
    @DisplayName("Test in service to simulates a for save method in class service")
    void save_PersistsARoute_ThenReturnsARouteResponse_WhenSuccesfull() {

        BDDMockito.when(routeRepository.save(RouteMapper.INTANCE.toRouteFromRouteRDTO(createsRequestDTO())))
                .thenReturn(createValidRoute());

        RouteResponseDTO routeResponseDTO = serviceTest.save(createsRequestDTO());

        Assertions.assertThat(routeResponseDTO.getId()).isNotNull();
        Assertions.assertThat(routeResponseDTO).isNotNull();


    }

    @Test
    void findById_ReturnsARouteResponse_WhenSuccesfull() throws RouteNotFoundException {

        BDDMockito.when(routeRepository.findById(1L))
                .thenReturn(Optional.of(createValidRoute()));

        RouteResponseDTO routeResponseDTO = serviceTest.findById(1l);

        Assertions.assertThat(routeResponseDTO).isNotNull();
        Assertions.assertThat(routeResponseDTO.getId()).isEqualTo(1);
    }

    @Test
    void replace_ReplacesARoute_WhenSucessfull() {

        BDDMockito.when(routeRepository.findById(1L))
                .thenReturn(Optional.of(createValidRoute()));

        BDDMockito.when(stopsRepository.findById(1L))
                .thenReturn(Optional.of(createValidStop()));


        Assertions.assertThatCode(() -> serviceTest.replace(createValidRoutePutDTO()))
                .doesNotThrowAnyException();

    }

    @Test
    void deleteById_DeletesARouteWhenSucessfull() {


        BDDMockito.when(routeRepository.findById(1L))
                .thenReturn(Optional.of(createValidRoute()));

        BDDMockito.doNothing().when(routeRepository).deleteById(1L);


        Assertions.assertThatCode(() -> serviceTest.deleteById(1l))
                .doesNotThrowAnyException();

    }

    @Test
    void findById_ThrowsRouteNotFoundException_WhenRouteNotFound() {

        Long id = 1l;

        BDDMockito.when(routeRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() ->serviceTest.findByIdOrThrowRouteNotFoundException(id))
                .isInstanceOf(RouteNotFoundException.class)
                .hasMessage("Route with " + id + " not found ");

    }

    @Test
    void shouldThrowStopsNotFoundException_WhenTriesToUpdateAnInvalidStops() {

        Long id = 1l;

        BDDMockito.when(routeRepository.findById(1L))
                .thenReturn(Optional.of(createValidRoute()));

        BDDMockito.when(stopsRepository.findById(id))
                .thenReturn(Optional.empty());


        Assertions.assertThatThrownBy(() ->serviceTest.replace(createValidRoutePutDTO()))
                .isInstanceOf(StopsNotFoundException.class)
                .hasMessage("Stops with " + id + " not found ");

    }







    // Auxiliary methods

    private RouteResponseDTO returnsRouteResponseDTO(){

        StopResponseDTO listStopsRDTO = StopsResponseDTOCreator.createSRDTO(1l,
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                StopStatus.ANSWER,
                45);

        List<StopResponseDTO> list = new ArrayList<>();
        list.add(listStopsRDTO);

        RouteResponseDTO routeResponseDTO = RouteResponseDTOCreator.createRRDTO(1l, OffsetDateTime.now(),
                RouteStatus.NOT_STARTED, list);

        return routeResponseDTO;

    }



    private Route createValidRoute() {

        Stops stops = StopsCreator.createStops(1L,
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                45,
                StopStatus.ANSWER);

        List<Stops> listStops = new ArrayList<>();
        listStops.add(stops);

        Route route = RouteCreator.createRoute(1L, OffsetDateTime.now(), RouteStatus.NOT_STARTED, listStops);
        return route;
    }


    private Stops createValidStop() {

        Stops stops = StopsCreator.createStops(1L,
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                45,
                StopStatus.ANSWER);


        return stops;
    }



    private RouteRequestDTO createsRequestDTO() {

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
        return routeRDTO;

    }


    private RoutePutDTO createValidRoutePutDTO(){

        StopsPutDTO stopsPutDTO = StopsPutDTOCreator.createStopsPDTO(1l,
                "India Prime Prime - Delivery iFood ",
                "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-341",
                -3.751316,
                -38.4841062,
                StopStatus.ANSWER,
                45);

        List<StopsPutDTO> stopsPDTO = new ArrayList<>();
        stopsPDTO.add(stopsPutDTO);

        RoutePutDTO routePutDTO = RoutePutDTOCreator.createPDTO(1L, RouteStatus.NOT_STARTED, OffsetDateTime.now(),
                stopsPDTO);

        return routePutDTO;
    }


}