package com.alisson.userapi.domain.user;

import com.alisson.userapi.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDTO {

    private Long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private String userPassword;
    private UserRole role;
}
