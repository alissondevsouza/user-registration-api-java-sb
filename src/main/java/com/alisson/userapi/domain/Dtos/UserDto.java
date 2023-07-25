package com.alisson.userapi.domain.Dtos;

import com.alisson.userapi.domain.entity.User;
import com.alisson.userapi.enums.UserRole;

import java.io.Serial;
import java.io.Serializable;

public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private UserRole role;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
        this.role = user.getRole();
    }

    public UserRole getRole() {

        return role;
    }

    public void setRole(UserRole role) {

        this.role = role;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserEmail() {

        return userEmail;
    }

    public void setUserEmail(String userEmail) {

        this.userEmail = userEmail;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public void setUserLogin(String userLogin) {

        this.userLogin = userLogin;
    }
}
