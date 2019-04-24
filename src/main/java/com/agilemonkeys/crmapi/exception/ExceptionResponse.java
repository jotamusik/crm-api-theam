package com.agilemonkeys.crmapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String errorCode;
    private List<ExceptionResponseError> errors;
}
