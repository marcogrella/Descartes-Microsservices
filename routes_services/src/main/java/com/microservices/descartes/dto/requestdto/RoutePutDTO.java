package com.microservices.descartes.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservices.descartes.model.RouteStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoutePutDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of a route", example = "1")
    @Positive(message = "The field 'id' should be a positive number")
    @NotNull(message = "The field 'id' cannot be null")
    private Long id;

    @ApiModelProperty(value = "status of route ", example = "STARTED, NOT_STARTED or DONE")
    @NotNull(message = "Field 'status' is mandatory")
    private RouteStatus status;

    @ApiModelProperty(value = "date of a route", example = "2022-01-23T14:14:15.3445122-03:00")
    @NotNull(message = "Field 'date' is mandatory")
    private OffsetDateTime date;

    @Valid
    @NotEmpty(message = "A list with at least one stops is required")
    private List<StopsPutDTO> stops;


}
