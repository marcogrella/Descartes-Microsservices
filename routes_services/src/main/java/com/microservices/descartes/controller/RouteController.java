package com.microservices.descartes.controller;

import com.microservices.descartes.dto.requestdto.RoutePutDTO;
import com.microservices.descartes.dto.requestdto.RouteRequestDTO;
import com.microservices.descartes.dto.responsedto.RouteResponseDTO;
import com.microservices.descartes.exceptions.RouteNotFoundException;
import com.microservices.descartes.exceptions.StopsNotFoundException;
import com.microservices.descartes.service.RouteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/route")
public class RouteController {

    @Autowired
    private final RouteService service;


    @ApiOperation(value = "Saves a route.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "When creates a valid route."),
            @ApiResponse(code = 400, message = "When occurs a bad request."),
            @ApiResponse(code = 500, message = "When occurs an Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<RouteResponseDTO> save(@RequestBody @Valid RouteRequestDTO routeRequestDTO){
        return new ResponseEntity(service.save(routeRequestDTO), HttpStatus.CREATED);

    }

    @ApiOperation(value = "Recovers a single route from database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When successfully returns a from database."),
            @ApiResponse(code = 400, message = "When occurs a bad request."),
            @ApiResponse(code = 500, message = "When occurs an Internal Server Error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> recover(@PathVariable Long id) throws RouteNotFoundException {
        return new ResponseEntity(service.findById(id), HttpStatus.OK);

    }

    @ApiOperation(value = "Deletes a route from database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When successfully deletes a route in database."),
            @ApiResponse(code = 400, message = "When occurs a bad request."),
            @ApiResponse(code = 500, message = "When occurs an Internal Server Error"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws RouteNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @ApiOperation(value = "Updates a route and/or related stops.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "When updates a route in database."),
            @ApiResponse(code = 400, message = "When occurs a bad request."),
            @ApiResponse(code = 500, message = "When occurs an Internal Server Error."),
    })
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid RoutePutDTO routePutDTO) throws RouteNotFoundException, StopsNotFoundException {
        service.replace(routePutDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
