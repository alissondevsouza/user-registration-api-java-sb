package com.alisson.userapi.service;

import com.alisson.userapi.domain.Dtos.AuthenticationDto;
import com.alisson.userapi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public String login(AuthenticationDto authenticationDto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.userLogin(), authenticationDto.userPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }
}
