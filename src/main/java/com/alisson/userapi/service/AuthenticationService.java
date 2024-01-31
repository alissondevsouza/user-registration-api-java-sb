package com.alisson.userapi.service;

import com.alisson.userapi.domain.auth.AuthenticationDTO;
import com.alisson.userapi.domain.user.User;
import com.alisson.userapi.exceptionHandling.exceptions.LoginNotFoundException;
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

    public String login(AuthenticationDTO authenticationDto) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(authenticationDto.userLogin(), authenticationDto.userPassword());
        try{
            var auth = this.authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        }catch (Exception e) {
            throw new LoginNotFoundException("Login or password invalid");
        }
    }
}
