package com.alisson.userapi.service;

import com.alisson.userapi.domain.Dtos.UserDto;
import com.alisson.userapi.domain.entity.User;
import com.alisson.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> findById(Long id) {

        try {
            Optional<User> user = userRepository.findById(id);
            return user.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(new UserDto(value)))
                    .orElseGet(() -> ResponseEntity.status(404).body("Error: User ID " + id + " not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> findAll() {

        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found!");
            }
            return ResponseEntity.ok().body(users.stream().map(UserDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> create(User user) {

        if (findByUserName(user) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Username already registered!");
        }

        if (findByUserEmail(user) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: UserEmail already registered!");
        }

        if (findByUserLogin(user) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: UserLogin already registered!");
        }

        try {
            User newUser = userRepository.save(new User(
                    null, user.getUserName(), user.getUserEmail(), user.getUserLogin(), encryptedPassword(user), user.getRole()
            ));

            return ResponseEntity.status(HttpStatus.CREATED).body("User ID " + newUser.getId() + " registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> update(Long id, User user) {

        try {
            if (findByUserName(user) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Username already registered!");
            }

            if (findByUserEmail(user) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: UserEmail already registered!");
            }

            if (findByUserLogin(user) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: UserLogin already registered!");
            }

            User oldUser = userRepository.findByIdNoOptional(id);

            oldUser.setUserName(user.getUserName());
            oldUser.setUserEmail(user.getUserEmail());
            oldUser.setUserLogin(user.getUserLogin());
            oldUser.setUserPassword(encryptedPassword(user));
            oldUser.setRole(user.getRole());

            userRepository.save(oldUser);

            return ResponseEntity.ok().body("User ID " + id + " updated successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> delete(Long id) {

        try {
            if (userRepository.findByIdNoOptional(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User ID " + id + " not found!");
            }

            userRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("User ID " + id + " deleted successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    private User findByUserName(User user) {

        User userValidation = userRepository.findByUserName(user.getUserName());

        if (userValidation != null) {
            return user;
        }
        return null;
    }

    private User findByUserEmail(User user) {

        User userValidation = userRepository.findByUserEmail(user.getUserEmail());

        if (userValidation != null) {
            return user;
        }
        return null;
    }

    private User findByUserLogin(User user) {

        UserDetails userValidation = userRepository.findByUserLogin(user.getUserLogin());

        if (userValidation != null) {
            return user;
        }
        return null;
    }

    public String encryptedPassword(User user) {

        return new BCryptPasswordEncoder().encode(user.getPassword());
    }
}
