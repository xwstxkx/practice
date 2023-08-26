package com.protasevich.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ObjectNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Объект с такими параметрами не существует")
    public ResponseError handle(ObjectNotFound exception) {
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParametersNotSpecified.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Недостаточно параметров в запросе")
    public ResponseError handle(ParametersNotSpecified exception) {
        return new ResponseError(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}