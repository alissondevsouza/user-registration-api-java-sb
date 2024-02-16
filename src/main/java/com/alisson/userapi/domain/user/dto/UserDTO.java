package com.alisson.userapi.domain.user.dto;

import com.alisson.userapi.domain.user.RequestUser;
import com.alisson.userapi.domain.user.entity.User;
import com.alisson.userapi.enums.UserRole;
import lombok.Getter;

@Getter
public class UserDTO {

    private final Long id;
    private final String userName;
    private final String userEmail;
    private final String userLogin;
    private String userPassword;
    private final UserRole role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.role = user.getRole();
    }

    public UserDTO(RequestUser user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.role = user.getRole();
    }

    public UserDTO(Long id, String userName, String userEmail, String userLogin, UserRole role) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.role = role;
    }
}
