package com.alisson.userapi.Controllers;

import com.alisson.userapi.Services.UserService;
import com.alisson.userapi.domain.Dtos.UserDto;
import com.alisson.userapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/{id}")
    public ResponseEntity <UserDto> finById(@PathVariable Long id ) {
        UserDto userDto = new UserDto(userService.findById(id));

        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping
    public ResponseEntity <List<UserDto>> findAll() {
        List<UserDto> listUsersDto = userService.findAll().stream().map( user ->
                new UserDto(user)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listUsersDto);
    }

    @PostMapping
    public ResponseEntity <UserDto> create (@RequestBody User user) {
        UserDto newUser = userService.create(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <UserDto> update(@PathVariable Long id, @RequestBody User user) {
        UserDto newUserDto = userService.update(id, user);

        return ResponseEntity.ok().body(newUserDto);
    }

   @DeleteMapping(value = "/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
