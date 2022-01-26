package com.microservices.descartes.dto.requestdto;

import com.microservices.descartes.model.RouteStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "status of route ", example = "STARTED, NOT_STARTED or DONE")
    @NotNull(message = "Field 'status' is mandatory")
    private RouteStatus status;

    @ApiModelProperty(value = "list of stop that belongs of the route.")
    @Valid
    @NotEmpty(message = "A list with at least one stops is required")
    private List<StopsRequestDTO> stops;


}
