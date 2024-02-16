package com.alisson.userapi.domain.user.dto;

import com.alisson.userapi.enums.UserRole;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final Long id;
    private final String userName;
    private final String userEmail;
    private final String userLogin;
    private final UserRole role;

    public UserResponseDTO(UserDTO user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
        this.role = user.getRole();
    }
}
