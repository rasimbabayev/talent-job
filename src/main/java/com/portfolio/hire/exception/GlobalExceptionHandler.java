package com.portfolio.hire.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static com.portfolio.hire.model.constants.ExceptionConstants.BAD_INPUT_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle() {

        return ExceptionResponse.of(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(NotFoundException ex) {

        return ExceptionResponse.of(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(LessThanOneShiftException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(LessThanOneShiftException ex) {

        return ExceptionResponse.of(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MissingServletRequestParameterException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ExceptionResponse handle(HttpRequestMethodNotSupportedException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(ConstraintViolationException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(HttpMessageNotReadableException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, Objects.requireNonNull(ex.getMessage()).split(":")[0]);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MethodArgumentTypeMismatchException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, Objects.requireNonNull(ex.getMessage()).split(";")[0]);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MissingRequestHeaderException ex) {

        return ExceptionResponse.of(BAD_INPUT_CODE, Objects.requireNonNull(ex.getMessage()).split(";")[0]);
    }
}
