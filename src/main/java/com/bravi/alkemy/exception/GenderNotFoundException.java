package com.bravi.alkemy.exception;

public class GenderNotFoundException extends RuntimeException {

    public GenderNotFoundException(Long id) {
        super("Gender with ID: " + id + " was not found.");
    }
}
