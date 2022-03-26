package com.bravi.alkemy.exception;

public class UserNotRegisteredException extends RuntimeException{

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
