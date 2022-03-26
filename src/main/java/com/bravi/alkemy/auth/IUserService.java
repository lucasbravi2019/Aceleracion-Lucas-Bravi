package com.bravi.alkemy.auth;

import com.bravi.alkemy.auth.dto.LoginRequestDTO;
import com.bravi.alkemy.auth.dto.LoginResponseDTO;
import com.bravi.alkemy.auth.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

public interface IUserService {

    UserEntity getUser(Long id) throws NoSuchElementException;
    UserEntity getUser(String username) throws NoSuchElementException;
    UserDTO registerUser(UserDTO dto) throws Exception;
    LoginResponseDTO login(LoginRequestDTO dto);
    LoginResponseDTO refreshToken(HttpServletRequest request) throws Exception;

}
