package com.alisson.userapi.controller;

import com.alisson.userapi.domain.auth.AuthenticationDTO;
import com.alisson.userapi.domain.auth.LoginResponseDTO;
import com.alisson.userapi.service.impl.AuthenticationServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthenticationController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDto) {

        var token = authenticationServiceImpl.login(authenticationDto);
        return ResponseEntity.ok(new LoginResponseDTO(HttpStatus.OK, token));
    }
}
