package com.microservices.descartes.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservices.descartes.model.StopStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StopResponseDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer deliveryRadius;
    private StopStatus status;


}
