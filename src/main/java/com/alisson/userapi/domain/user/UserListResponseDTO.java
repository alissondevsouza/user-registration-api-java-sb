package com.alisson.userapi.domain.user;

import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponseDTO {

    private final List<UserResponseDTO> users;

    public UserListResponseDTO(List<UserDTO> users) {
        this.users = users.stream().map(UserResponseDTO::new).toList();
    }
}
