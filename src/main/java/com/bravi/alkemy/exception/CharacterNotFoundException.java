package com.bravi.alkemy.exception;

public class CharacterNotFoundException extends RuntimeException{

    public CharacterNotFoundException(Long id) {
        super("Character with ID: " + id + " was not found.");
    }
}
