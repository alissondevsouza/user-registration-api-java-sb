package com.alisson.userapi.domain.Dtos;

import com.alisson.userapi.domain.User;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private String userPassword;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
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
