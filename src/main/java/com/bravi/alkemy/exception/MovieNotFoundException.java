package com.bravi.alkemy.exception;


public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(Long id) {
        super("Movie with ID: " + id + " was not found.");
    }

}
