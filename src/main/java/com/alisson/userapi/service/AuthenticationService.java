package com.alisson.userapi.service;

import com.alisson.userapi.domain.auth.AuthenticationDTO;

public interface AuthenticationService {

    String login(AuthenticationDTO authenticationDto);
}
