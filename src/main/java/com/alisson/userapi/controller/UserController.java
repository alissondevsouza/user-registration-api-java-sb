package com.alisson.userapi.controller;

import com.alisson.userapi.service.UserService;
import com.alisson.userapi.domain.Dtos.UserDto;
import com.alisson.userapi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> finById(@PathVariable Long id) {

        return ResponseEntity.ok().body(new UserDto(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {

        return ResponseEntity.ok().body(userService.findAll().stream().map(UserDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody User user) {

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(userService.create(user).getId())
                        .toUri())
                .build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody User user) {

        return ResponseEntity.ok().body(userService.update(id, user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
