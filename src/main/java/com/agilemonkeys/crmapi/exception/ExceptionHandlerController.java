package com.agilemonkeys.crmapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ExceptionResponse invalidInput(MethodArgumentNotValidException ex) {

        logger.warn("Catch MethodArgumentNotValidException -> " + ex.getMessage());

        BindingResult result = ex.getBindingResult();
        return new ExceptionResponse("validation_error", fromBindingErrors(result));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ExceptionResponse invalidInput(BadRequestException ex) {
        logger.warn("Catch BadRequestException -> " + ex.getExceptionResponse().getErrorCode());
        return ex.getExceptionResponse();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ExceptionResponse resourceNotFound(ResourceNotFoundException ex) {
        logger.warn("Catch ResourceNotFoundException -> " + ex.getExceptionResponse().getErrorCode());
        return ex.getExceptionResponse();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public ExceptionResponse forbiddenAction(ForbiddenException ex) {
        logger.warn("Catch ForbiddenException -> " + ex.getExceptionResponse().getErrorCode());
        return ex.getExceptionResponse();
    }

    private List<ExceptionResponseError> fromBindingErrors(Errors errors) {
        List<ExceptionResponseError> validErrors = new ArrayList<>();
        for (FieldError objectError : errors.getFieldErrors()) {
            validErrors.add(new ExceptionResponseError(objectError.getField(), objectError.getCodes()[objectError.getCodes().length - 1].toLowerCase()));
        }
        return validErrors;
    }
}
