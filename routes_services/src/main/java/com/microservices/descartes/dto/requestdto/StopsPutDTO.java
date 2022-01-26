package com.microservices.descartes.dto.requestdto;

import com.microservices.descartes.model.StopStatus;
import com.microservices.descartes.validation.NonZeroValues;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StopsPutDTO implements Serializable {

    private static final long serialVersionUID = -1893114169831542502L;


    @Positive(message = "The field 'id' should be a positive number")
    @NotNull(message = "The field 'id' cannot be null")
    private Long id;

    @ApiModelProperty(value = "Description of a stop", example = "India Prime Prime")
    @NotEmpty(message = "The field 'description' cannot be blank neither null.")
    @Size(min = 2, max = 50, message = "The field 'description' must be between 2 and 50 characters")
    private String description;

    @ApiModelProperty(value = "Address of a stop", example = "Av. Washington Soares, 723 - Edson Queiroz, Fortaleza - CE, 60811-34")
    @NotEmpty(message = "The field 'address' cannot be blank neither null.")
    @Size(min = 2, max = 100, message = "The field 'address' must be between 5 and 100 characters")
    private String address;

    @ApiModelProperty(value = "latitude of a stop", example = "1.35755")
    @NotNull(message = "The field 'latitude' cannot be null")
    @Min(value = -180, message = "The field latitude must be between -180 and 180")
    @Max(value = 180, message = "The field latitude must be between -180 and 180")
    @NonZeroValues(message = "Field 'latitude' does not accepts zero values")
    private Double latitude;

    @ApiModelProperty(value = "longitude of a stop", example = "-38.4841062")
    @NotNull(message = "The field 'longitude' cannot be null")
    @Min(value = -180, message = "The field longitude must be between -180 and 180")
    @Max(value = 180, message = "The field longitude must be between -180 and 180")
    @NonZeroValues(message = "Field 'longitude' does not accepts zero values")
    private Double longitude;

    @ApiModelProperty(value = "status of a stop", example = "ANSWER or NOT_ANSWER")
    @NotNull(message = "Field 'status' cannot be null")
    private StopStatus status;

    @ApiModelProperty(value = "delivery radius of a stop", example = "10")
    @Positive(message = "The field 'deliveryRadius' should be a positive number")
    @Min(value = 0, message = "The field 'deliveryRadius' must be between 0 and 100")
    @Max(value = 100, message = "The field 'deliveryRadius' must be between 0 and 100")
    private Integer deliveryRadius;

}
