package com.agilemonkeys.crmapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ForbiddenException extends RuntimeException {

    private ExceptionResponse exceptionResponse;

    public ForbiddenException(ExceptionResponse ex) {
        exceptionResponse = ex;
    }

    public ForbiddenException(ExceptionResponseError ex) {
        List<ExceptionResponseError> validErrors = new ArrayList<>();
        validErrors.add(ex);

        exceptionResponse = new ExceptionResponse("forbidden_exception", validErrors);
    }
}
