package com.meter.adapter.exception;
import java.net.URI;

public final class ErrorConstants {

    static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    static final String ERR_VALIDATION = "error.validation";
    private static final String PROBLEM_BASE_URL = "https://www.sagemcom.com/problem";
    static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    static final URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/entity-not-found");

    private ErrorConstants() {
    }
}