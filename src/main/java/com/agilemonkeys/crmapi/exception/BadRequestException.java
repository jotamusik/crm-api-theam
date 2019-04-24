package com.agilemonkeys.crmapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private ExceptionResponse exceptionResponse;

    public BadRequestException(ExceptionResponse ex) {
        exceptionResponse = ex;
    }

    public BadRequestException(ExceptionResponseError ex) {
        List<ExceptionResponseError> validErrors = new ArrayList<>();
        validErrors.add(ex);

        exceptionResponse = new ExceptionResponse("validation_error", validErrors);
    }
}
