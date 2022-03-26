package com.bravi.alkemy.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequestDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String username;
    private String password;


}
