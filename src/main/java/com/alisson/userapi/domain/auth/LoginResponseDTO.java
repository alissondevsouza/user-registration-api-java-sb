package com.alisson.userapi.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LoginResponseDTO{

    private HttpStatus status;
    private String token;
}
