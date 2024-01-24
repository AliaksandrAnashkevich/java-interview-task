package com.hehnosky.javainterviewtask.exception;

public class OperationNotFoundException extends RuntimeException {

    private static final String OPERATION_NOT_FOUND_MESSAGE_ERROR = "Operation with id=[%s] don't exist";

    public OperationNotFoundException(Long id) {
        super(String.format(OPERATION_NOT_FOUND_MESSAGE_ERROR, id));
    }
}
