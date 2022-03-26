package com.bravi.alkemy.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorDTO<T> extends Object {

    private HttpStatus status;
    private T errors;

}
