package com.alisson.userapi.service.impl;

import com.alisson.userapi.domain.auth.AuthenticationDTO;
import com.alisson.userapi.domain.user.entity.User;

import com.alisson.userapi.exceptionHandling.exceptions.LoginNotFoundException;

import com.alisson.userapi.service.AuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenServiceImpl;

    AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenServiceImpl tokenServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.tokenServiceImpl = tokenServiceImpl;
    }

    @Override
    public String login(AuthenticationDTO authenticationDto) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(authenticationDto.userLogin(), authenticationDto.userPassword());
        try{
            var auth = this.authenticationManager.authenticate(usernamePassword);
            return this.tokenServiceImpl.generateToken((User) auth.getPrincipal());
        }catch (Exception e) {
            throw new LoginNotFoundException("Login or password invalid");
        }
    }
}
