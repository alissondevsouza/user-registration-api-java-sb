package com.alisson.userapi.service;

import com.alisson.userapi.domain.user.entity.User;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);
}
