package com.alisson.userapi.Controllers;

import com.alisson.userapi.Services.UserService;
import com.alisson.userapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity <User> create (@RequestBody User user) {
        User newUser = userService.create(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <User> update(@PathVariable Long id, @RequestBody User user) {
        User newUser = new User(userService.update(id, user));

        return ResponseEntity.ok().body(newUser);
    }

   @DeleteMapping(value = "/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
