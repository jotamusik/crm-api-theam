package com.agilemonkeys.crmapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private ExceptionResponse exceptionResponse;

    public ResourceNotFoundException(ExceptionResponse ex) {
        exceptionResponse = ex;
    }

    public ResourceNotFoundException(ExceptionResponseError ex) {
        List<ExceptionResponseError> validErrors = new ArrayList<>();
        validErrors.add(ex);

        exceptionResponse = new ExceptionResponse("resource_exception", validErrors);
    }

}
