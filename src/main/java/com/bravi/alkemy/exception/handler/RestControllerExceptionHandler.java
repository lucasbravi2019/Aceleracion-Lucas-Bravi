package com.bravi.alkemy.exception.handler;

import com.bravi.alkemy.exception.*;
import com.bravi.alkemy.exception.dto.ApiErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return handleExceptionInternal(
                ex,
                new ApiErrorDTO<>(
                        HttpStatus.BAD_REQUEST,
                        errors
                ),
                headers,
                status,
                request
        );
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ApiErrorDTO<String>(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                ),
                headers,
                status,
                request
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MovieNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleMovieNotFound(MovieNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleCharacterNotFound(CharacterNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(GenderNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleGenderNotFound(GenderNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleUsernameNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EmailException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleEmailException(EmailException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserNotRegisteredException.class)
    protected ResponseEntity<ApiErrorDTO<String>> handleUserRegistration(UserNotRegisteredException ex) {
        return new ResponseEntity<>(
                new ApiErrorDTO<>(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}
