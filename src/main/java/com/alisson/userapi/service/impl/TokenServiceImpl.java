package com.alisson.userapi.service.impl;

import com.alisson.userapi.configuration.JwtTokenConfiguration;
import com.alisson.userapi.domain.user.entity.User;

import com.alisson.userapi.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    private final JwtTokenConfiguration jwtTokenConfiguration;

    public TokenServiceImpl(JwtTokenConfiguration jwtTokenConfiguration) {
        this.jwtTokenConfiguration = jwtTokenConfiguration;
    }

    @Override
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.jwtTokenConfiguration.getSecret());
            return JWT.create()
                    .withIssuer("user-registration")
                    .withSubject(user.getUserLogin())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.jwtTokenConfiguration.getSecret());
            return JWT.require(algorithm)
                    .withIssuer("user-registration")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
