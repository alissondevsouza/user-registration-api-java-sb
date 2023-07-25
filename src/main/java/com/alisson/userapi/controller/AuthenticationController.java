package com.alisson.userapi.controller;

import com.alisson.userapi.domain.Dtos.AuthenticationDto;
import com.alisson.userapi.domain.Dtos.LoginResponseDto;
import com.alisson.userapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto) {

        return ResponseEntity.ok(new LoginResponseDto(authenticationService.login(authenticationDto)));
    }
}
