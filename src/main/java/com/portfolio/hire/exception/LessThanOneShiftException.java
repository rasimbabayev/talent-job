package com.portfolio.hire.exception;

import lombok.Getter;

@Getter
public class LessThanOneShiftException extends RuntimeException {

    private final String code;

    public LessThanOneShiftException(String message, String code) {
        super(message);
        this.code = code;
    }
}
