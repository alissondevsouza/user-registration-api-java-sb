package com.alisson.userapi.controller;

import com.alisson.userapi.domain.user.*;
import com.alisson.userapi.domain.user.dto.UserDTO;
import com.alisson.userapi.domain.user.dto.UserListResponseDTO;
import com.alisson.userapi.domain.user.dto.UserResponseDTO;
import com.alisson.userapi.service.impl.UserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    public UserController( UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> finById(@PathVariable("id") Long userId){

        UserDTO userDTO = this.userServiceImpl.findById(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK ,new UserResponseDTO(userDTO)));
    }

    @GetMapping
    public ResponseEntity<ResponseUser> findAll() {

        List<UserDTO> listUserDTO = this.userServiceImpl.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK , new UserListResponseDTO(listUserDTO)));
    }

    @PostMapping
    public ResponseEntity<ResponseUser> create(@RequestBody RequestUser user) {

        UserDTO userDTO = this.userServiceImpl.create(new UserDTO(user));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUser(HttpStatus.CREATED , new UserResponseDTO(userDTO)));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseUser> update(@PathVariable("id") Long userId, @RequestBody RequestUser user) {

        UserDTO userDTO = userServiceImpl.update(userId, new UserDTO(user));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK ,new UserResponseDTO(userDTO)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseUser> delete(@PathVariable("id") Long userId) {

        String responseDeleted = userServiceImpl.delete(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK , responseDeleted));
    }
}
