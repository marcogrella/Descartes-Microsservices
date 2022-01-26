package com.microservices.descartes.dto.responsedto;


import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.Stops;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private OffsetDateTime date;
    private RouteStatus status;
    private List<StopResponseDTO> stops;

}
