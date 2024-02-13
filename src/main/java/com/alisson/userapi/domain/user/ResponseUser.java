package com.alisson.userapi.domain.user;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ResponseUser {

    private HttpStatus status;
    private Object data;

    public ResponseUser(HttpStatus status, UserResponseDTO data) {
        this.status = status;
        this.data = data;
    }

    public ResponseUser(HttpStatus status, UserListResponseDTO data) {
        this.status = status;
        this.data = data;
    }

    public ResponseUser(HttpStatus status, String message) {
        this.status = status;
        this.data = message;
    }
}
