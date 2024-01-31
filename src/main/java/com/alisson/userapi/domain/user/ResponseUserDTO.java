package com.alisson.userapi.domain.user;

import com.alisson.userapi.domain.user.UserDTO;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ResponseUserDTO {

    private HttpStatus status;
    private Object data;

    public ResponseUserDTO(HttpStatus status, UserDTO data) {
        this.status = status;
        this.data = data;
    }

    public ResponseUserDTO(HttpStatus status, List<UserDTO> data) {
        this.status = status;
        this.data = data;
    }
}
