package com.portfolio.hire.model.constants;

public interface ExceptionConstants {

    String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED_EXCEPTION";
    String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occurred";
    String BAD_INPUT_CODE = "BAD_INPUT";
    String JOB_NOT_FOUND_CODE = "JOB_NOT_FOUND";
    String JOB_NOT_FOUND_MESSAGE = "Job with id: %s not found";
    String SHIFT_NOT_FOUND_CODE = "SHIFT_NOT_FOUND";
    String SHIFT_NOT_FOUND_MESSAGE = "SHIFT with id: %s not found";
    String TALENT_NOT_FOUND_CODE = "TALENT_NOT_FOUND";
    String TALENT_NOT_FOUND_MESSAGE = "TALENT with id: %s not found";
    String LESS_THAN_ONE_SHIFT_CODE = "LESS_THAN_ONE_SHIFT";
    String LESS_THAN_ONE_SHIFT_MESSAGE = "Job should have at least 1 shift";
}
