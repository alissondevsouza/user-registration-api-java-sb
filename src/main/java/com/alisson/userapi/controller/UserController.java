package com.alisson.userapi.controller;

import com.alisson.userapi.domain.user.RequestUserDTO;
import com.alisson.userapi.domain.user.ResponseUserDTO;
import com.alisson.userapi.domain.user.User;
import com.alisson.userapi.domain.user.UserDTO;
import com.alisson.userapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> finById(@PathVariable("id") Long id){

        UserDTO user = this.userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUserDTO(HttpStatus.OK ,user));
    }

    @GetMapping
    public ResponseEntity<ResponseUserDTO> findAll() {

        List<UserDTO> users = this.userService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUserDTO(HttpStatus.OK ,users));
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> create(@RequestBody RequestUserDTO user) {

        UserDTO userDTO = this.userService.create(new UserDTO(user));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUserDTO(HttpStatus.CREATED ,userDTO));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam Long id, @RequestBody RequestUserDTO user) {

        UserDTO userDTO = userService.update(id, new UserDTO(user));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseUserDTO(HttpStatus.OK ,userDTO));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam Long id) {

        return userService.delete(id);
    }
}
