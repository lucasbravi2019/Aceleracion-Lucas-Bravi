package com.bravi.alkemy.auth;

import com.bravi.alkemy.auth.dto.LoginRequestDTO;
import com.bravi.alkemy.auth.dto.LoginResponseDTO;
import com.bravi.alkemy.auth.dto.UserDTO;
import com.bravi.alkemy.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) throws Exception {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
        } catch(Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(userService.refreshToken(request), HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
