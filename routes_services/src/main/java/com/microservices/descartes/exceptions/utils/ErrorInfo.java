package com.microservices.descartes.exceptions.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorInfo {

    private String field;
    private String fieldMessage;
}
