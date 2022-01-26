package com.microservices.descartes.exceptions.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpResponseBinding implements Serializable {


    private static final long serialVersionUID = 3673929602908078612L;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone ="America/Sao_Paulo")
    private Date timestamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String defaultMessage;
    List<ErrorInfo> message;

}
