package com.alisson.userapi.controller;

import com.alisson.userapi.domain.user.*;
import com.alisson.userapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> finById(@PathVariable("id") Long userId){

        UserDTO userDTO = this.userService.findById(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK ,new UserResponseDTO(userDTO)));
    }

    @GetMapping
    public ResponseEntity<ResponseUser> findAll() {

        List<UserDTO> listUserDTO = this.userService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK , new UserListResponseDTO(listUserDTO)));
    }

    @PostMapping
    public ResponseEntity<ResponseUser> create(@RequestBody RequestUser user) {

        UserDTO userDTO = this.userService.create(new UserDTO(user));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUser(HttpStatus.CREATED , new UserResponseDTO(userDTO)));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseUser> update(@PathVariable("id") Long userId, @RequestBody RequestUser user) {

        UserDTO userDTO = userService.update(userId, new UserDTO(user));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK ,new UserResponseDTO(userDTO)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseUser> delete(@PathVariable("id") Long userId) {

        String responseDeleted = userService.delete(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUser(HttpStatus.OK , responseDeleted));
    }
}
