package com.alisson.userapi.service.impl;

import com.alisson.userapi.domain.user.dto.UserDTO;
import com.alisson.userapi.domain.user.entity.User;
import com.alisson.userapi.exceptionHandling.exceptions.MissingParameterException;
import com.alisson.userapi.exceptionHandling.exceptions.UserNotFoundException;
import com.alisson.userapi.repository.UserRepository;

import com.alisson.userapi.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not exist!"));

        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> listUsers = userRepository.findAll();

        return listUsers
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        this.isParametersNotNull(userDTO);

        User user = this.convertToEntity(userDTO);

        this.isUserAlreadyExist(user);

        User newUser = userRepository.save(user);

        return new UserDTO(newUser);
    }

    @Override
    public UserDTO update(Long userId, UserDTO userDto) {

        User userToBeUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not exist!"));

        User newUserToUpdate = new User(userDto);

        this.updateUserFields(userToBeUpdate, newUserToUpdate);

        User updatedUser = userRepository.save(userToBeUpdate);

        return new UserDTO(updatedUser);
    }

    @Override
    public String delete(Long userId) {

        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not exist!"));

        userRepository.delete(userToDelete);

        return "User with ID " + userId + " has been successfully deleted!";
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserLogin(userDTO.getUserLogin());
        user.setUserPassword(this.encryptedPassword(userDTO.getUserPassword()));
        user.setRole(userDTO.getRole());
        return user;
    }

    private void isUserAlreadyExist (User user) {

        if (findByUserName(user) != null) {
            throw new UserNotFoundException("User name " + user.getUserName() + " already exists!");
        }

        if (findByUserEmail(user) != null) {
            throw new UserNotFoundException("User email " + user.getUserEmail() + " already exists!");
        }

        if (findByUserLogin(user) != null) {
            throw new UserNotFoundException("User login " + user.getUserLogin() + " already exists!");
        }
    }

    private void isParametersNotNull(UserDTO userDTO) {
        if (userDTO.getUserName() == null || userDTO.getUserName().isEmpty()) {
            throw new MissingParameterException("User name is required!");
        }
        if(userDTO.getUserEmail() == null || userDTO.getUserEmail().isEmpty()) {
            throw new MissingParameterException("User email is required!");
        }
        if(userDTO.getUserLogin() == null || userDTO.getUserLogin().isEmpty()) {
            throw new MissingParameterException("User login is required!");
        }
        if(userDTO.getUserPassword() == null || userDTO.getUserPassword().isEmpty()) {
            throw new MissingParameterException("User password is required!");
        }
        if(userDTO.getRole() == null || userDTO.getRole().toString().isEmpty()) {
            throw new MissingParameterException("User role is required!");
        }
    }

    private void updateUserFields(User userToBeUpdate, User newUserToUpdate) {
        if (newUserToUpdate.getUserName() != null && !newUserToUpdate.getUserName().isEmpty()) {
            userToBeUpdate.setUserName(newUserToUpdate.getUserName());
        }
        if (newUserToUpdate.getUserEmail() != null && !newUserToUpdate.getUserEmail().isEmpty()) {
            userToBeUpdate.setUserEmail(newUserToUpdate.getUserEmail());
        }
        if (newUserToUpdate.getUserLogin() != null && !newUserToUpdate.getUserLogin().isEmpty()) {
            userToBeUpdate.setUserLogin(newUserToUpdate.getUserLogin());
        }
        if (newUserToUpdate.getUserPassword() != null && !newUserToUpdate.getUserPassword().isEmpty()) {
            userToBeUpdate.setUserPassword(encryptedPassword(newUserToUpdate.getUserPassword()));
        }
        if (newUserToUpdate.getRole() != null && !newUserToUpdate.getRole().toString().isEmpty()){
            userToBeUpdate.setRole(newUserToUpdate.getRole());
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

    @Override
    public String encryptedPassword(String userPassword) {

        return new BCryptPasswordEncoder().encode(userPassword);
    }
}
