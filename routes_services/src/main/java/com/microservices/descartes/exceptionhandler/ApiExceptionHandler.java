package com.microservices.descartes.exceptionhandler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.microservices.descartes.exceptions.RouteNotFoundException;
import com.microservices.descartes.exceptions.StopsNotFoundException;
import com.microservices.descartes.exceptions.utils.ErrorInfo;
import com.microservices.descartes.exceptions.utils.HttpResponse;
import com.microservices.descartes.exceptions.utils.HttpResponseBinding;
import com.microservices.descartes.model.RouteStatus;
import com.microservices.descartes.model.StopStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity handleRouteNotFoundException (RouteNotFoundException routeNotFoundException) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, routeNotFoundException.getMessage());
    }

    @ExceptionHandler(StopsNotFoundException.class)
    public ResponseEntity handleStopsNotFoundException (StopsNotFoundException stopsNotFoundException) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, stopsNotFoundException.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseBinding> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErrorInfo> errorList = new ArrayList<>();

        for(int i =0; i < e.getBindingResult().getFieldErrors().size(); i++){
            ErrorInfo errorsInfo = new ErrorInfo();
            errorsInfo.setField(fieldErrors.get(i).getField());
            errorsInfo.setFieldMessage(fieldErrors.get(i).getDefaultMessage());
            errorList.add(errorsInfo);
        }

        return createHttpResponseWithBindingResults(HttpStatus.BAD_REQUEST, e.getMessage(), errorList);
    }


    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<HttpResponseBinding> handleInvalidFormatException(InvalidFormatException e) {

        List<ErrorInfo> errorInfoList = new ArrayList<>();


        if(e.getTargetType().isAssignableFrom(RouteStatus.class)){
            ErrorInfo errorsInfo = new ErrorInfo();
            errorsInfo.setField("status");
            errorsInfo.setFieldMessage(e.getValue() + " is not valid for status field (valid values: STARTED, " +
                    "NOT_STARTED or DONE");
            errorInfoList.add(errorsInfo);
        }

        if(e.getTargetType().isAssignableFrom(StopStatus.class)){
            ErrorInfo errorsInfo = new ErrorInfo();
            errorsInfo.setField("status");
            errorsInfo.setFieldMessage(e.getValue() + " is not valid for status field (valid values: ANSWER or NOT_ANSWER)");
            errorInfoList.add(errorsInfo);
        }

        return createHttpResponseWithBindingResults(HttpStatus.BAD_REQUEST, e.getMessage(), errorInfoList);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResponseBinding> handleInvalidFormatException(HttpMessageNotReadableException e) {

        List<ErrorInfo> errorInfoList = new ArrayList<>();

        ErrorInfo errorsInfo = new ErrorInfo();
        errorsInfo.setField("status");
        errorsInfo.setFieldMessage(" Invalid information: " + e.getMostSpecificCause());
        errorInfoList.add(errorsInfo);


        return createHttpResponseWithBindingResults(HttpStatus.BAD_REQUEST, e.getMessage(), errorInfoList);
    }



    /* Generics - Internal Server Error 500 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error. Please try to contact" +
                " the system administrator");
    }






    // Simple Exception Response
    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        HttpResponse httpResponseBody = new HttpResponse(new Date(), httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase(), message) {
        };
        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }


    // Binding Results Exception Response
    private ResponseEntity<HttpResponseBinding> createHttpResponseWithBindingResults(HttpStatus httpStatus, String message,
                                                                                     List<ErrorInfo> errorsInfoList){

        HttpResponseBinding httpResponseBody = new HttpResponseBinding(new Date(), httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase(), message, errorsInfoList);

        return new ResponseEntity<>(httpResponseBody, httpStatus);
    }








}
