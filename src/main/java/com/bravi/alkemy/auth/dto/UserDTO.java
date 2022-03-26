package com.bravi.alkemy.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @Email(message = "must be a valid email")
    private String username;

    @Size(min = 8, message = "must have more than 8 characters")
    private String password;

}
