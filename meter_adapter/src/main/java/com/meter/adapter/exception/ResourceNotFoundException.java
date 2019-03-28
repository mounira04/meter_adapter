package com.meter.adapter.exception;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ResourceNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(final String title) {
        super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, title, Status.NOT_FOUND, "idexists");
    }
}
