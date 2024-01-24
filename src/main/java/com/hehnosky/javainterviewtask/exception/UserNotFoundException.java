package com.hehnosky.javainterviewtask.exception;

public class UserNotFoundException extends RuntimeException{

    private static final String USER_NOT_FOUND_MESSAGE_ERROR = "User with id=[%s] don't exist";

    public UserNotFoundException(Long id) {
        super(String.format(USER_NOT_FOUND_MESSAGE_ERROR, id));
    }
}
