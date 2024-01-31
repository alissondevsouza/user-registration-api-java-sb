package com.alisson.userapi.service;

import com.alisson.userapi.domain.user.UserDTO;
import com.alisson.userapi.domain.user.User;
import com.alisson.userapi.exceptionHandling.exceptions.UserNotFoundException;
import com.alisson.userapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User ID " + id + " not found!"));

        return new UserDTO(user);
    }

    public List<UserDTO> findAll() {

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO create(UserDTO userDTO) {

        User user = new User(
                userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getUserEmail(),
                userDTO.getUserLogin(),
                encryptedPassword(new User(userDTO)),
                userDTO.getRole());

       User newUser = userRepository.save(user);

       return new UserDTO(newUser);
    }

    public UserDTO update(Long id, UserDTO userDto) {

        User oldUser = userRepository.findByIdNoOptional(id);

        User user = new User(userDto);

        oldUser.setUserName(user.getUsername());
        oldUser.setUserEmail(user.getUserEmail());
        oldUser.setUserLogin(user.getUserLogin());
        oldUser.setUserPassword(encryptedPassword(user));
        oldUser.setRole(user.getRole());

        User responseUser = userRepository.save(oldUser);

        return new UserDTO(responseUser);

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

    private UserDTO findByUserName(UserDTO user) {

        User userValidation = userRepository.findByUserName(user.getUserName());

        if (userValidation != null) {
            return user;
        }
        return null;
    }

    private UserDTO findByUserEmail(UserDTO user) {

        User userValidation = userRepository.findByUserEmail(user.getUserEmail());

        if (userValidation != null) {
            return user;
        }
        return null;
    }

    private UserDTO findByUserLogin(UserDTO user) {

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
