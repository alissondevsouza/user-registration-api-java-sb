package com.alisson.userapi.controller;

import com.alisson.userapi.domain.entity.User;
import com.alisson.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/id")
    public ResponseEntity<Object> finById(@RequestParam Long id) throws RuntimeException {

        return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user) {

        return userService.create(user);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam Long id, @RequestBody User user) {

        return userService.update(id, user);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam Long id) {

        return userService.delete(id);
    }
}
