package com.alisson.userapi.controller;

import com.alisson.userapi.domain.auth.AuthenticationDTO;
import com.alisson.userapi.domain.auth.LoginResponseDTO;
import com.alisson.userapi.service.AuthenticationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDto) {

        var token = authenticationService.login(authenticationDto);
        return ResponseEntity.ok(new LoginResponseDTO(HttpStatus.OK, token));
    }
}
