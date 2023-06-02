package com.alisson.userapi.Controllers;

import com.alisson.userapi.Services.UserService;
import com.alisson.userapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/{id}")
    public ResponseEntity <User> finById(@PathVariable Long id ) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity <List<User>> findAll() {
        List<User> listUsers = userService.findAll();

        return ResponseEntity.ok().body(listUsers);
    }
}
