package com.alisson.userapi.service.impl;

import com.alisson.userapi.domain.user.dto.UserDTO;
import com.alisson.userapi.domain.user.entity.User;
import com.alisson.userapi.enums.UserRole;
import com.alisson.userapi.exceptionHandling.exceptions.MissingParameterException;
import com.alisson.userapi.exceptionHandling.exceptions.UserAlreadyExistException;
import com.alisson.userapi.exceptionHandling.exceptions.UserNotFoundException;
import com.alisson.userapi.repository.UserRepository;
import com.alisson.userapi.utils.UserUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest  {

    @Mock
    UserRepository userRepository;

    @Mock
    UserUtil userUtil;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("Should find user by id successfully")
    void findByIdCase1() throws UserNotFoundException {

        User user = new User(
                1L,
                "User Test 1",
                "usertest1@gmail.com",
                "usertest1",
                "123456",
                UserRole.USER
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.findById(1L);
        verify(userRepository, times(1)).findById(any());

        Assertions.assertEquals(user.getUserName(), result.getUserName());
        Assertions.assertEquals(user.getUserEmail(), result.getUserEmail());
        Assertions.assertEquals(user.getUserLogin(), result.getUserLogin());
        Assertions.assertEquals(user.getRole(), result.getRole());
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user not found by id")
    void findByIdCase2() throws UserNotFoundException {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.findById(1L);
        });

        Assertions.assertEquals("User with ID 1 not exist!", exception.getMessage());
    }

    @Test
    @DisplayName("Should find all users successfully")
    void findAllCase1() {
        List<User> users = List.of(
                new User(
                        1L,
                        "User Test 1",
                        "usertest1@gmail.com",
                        "usertest1",
                        "123456",
                        UserRole.USER
                ),
                new User(
                        2L,
                        "User Test 2",
                        "usertest2@gmail.com",
                        "usertest2",
                        "123456",
                        UserRole.USER
                ),
                new User(
                        3L,
                        "User Test 3",
                        "usertest3@gmail.com",
                        "usertest3",
                        "123456",
                        UserRole.USER
                )
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.findAll();
        verify(userRepository, times(1)).findAll();

        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    @DisplayName("Should return an empty array because it does not find users ")
    void findAllCase2() {

        when(userRepository.findAll()).thenReturn(List.of());

        List<UserDTO> result = userService.findAll();
        verify(userRepository, times(1)).findAll();

        Assertions.assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Should create user successfully")
    void createCase1() {
        UserDTO newUser = new UserDTO(
                1L,
                "User Test 1",
                "usertest1@gmail.com",
                "usertest1",
                "123456",
                UserRole.USER
        );

        when(userRepository.save(any())).thenReturn(new User(newUser));

        UserDTO result = userService.create(newUser);
        verify(userRepository, times(1)).save(any());

        Assertions.assertEquals(newUser.getUserName(), result.getUserName());
        Assertions.assertEquals(newUser.getUserEmail(), result.getUserEmail());
        Assertions.assertEquals(newUser.getUserLogin(), result.getUserLogin());
        Assertions.assertEquals(newUser.getRole(), result.getRole());
    }

    @Test
    @DisplayName("Should throw MissingParameterException when any parameter is null")
    void createCase2() {

        doThrow(new MissingParameterException("User name is required!"))
                .when(userUtil)
                .isParametersNotNull(any());

        MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
            userService.create(any());
        });

        Assertions.assertEquals("User name is required!", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw UserAlreadyExistException when user already exist")
    void createCase3() {

        User user = new User(
                1L,
                "User Test 1",
                "usertest3@gmail.com",
                "usertest1",
                "123456",
                UserRole.USER
        );

        when(userRepository.save(any())).thenThrow(new UserAlreadyExistException("User name User Test 1 already exists!") );

        UserAlreadyExistException exception = Assertions.assertThrows(UserAlreadyExistException.class, () -> {
            userService.create(new UserDTO(user));
        });

        Assertions.assertEquals("User name User Test 1 already exists!", exception.getMessage());
    }

    @Test
    @DisplayName("Should update user successfully")
    void updateCase1() {
        User user = new User(
                1L,
                "User Test 1",
                "usertest1@gmail.com",
                "usertest1",
                "123456",
                UserRole.USER
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        UserDTO result = userService.update(1L, new UserDTO(user));
        verify(userRepository, times(1)).save(any());

        Assertions.assertEquals(user.getUserName(), result.getUserName());
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user not found by id")
    void updateCase2() throws UserNotFoundException {

        when(userRepository.findById(1L)).thenThrow(new UserNotFoundException("User with ID 1 not exist!"));

        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.update(1L, any());
        });

        Assertions.assertEquals("User with ID 1 not exist!", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete user successfully")
    void deleteCase1() {
        User user = new User(
                1L,
                "User Test 1",
                "usertest1@gmail.com",
                "usertest1",
                "123456",
                UserRole.USER
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any());

        userService.delete(1L);
        verify(userRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user not found by id")
    void deleteCase2() {

        when(userRepository.findById(1L)).thenThrow(new UserNotFoundException("User with ID 1 not exist!"));

        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.delete(1L);
        });

        Assertions.assertEquals("User with ID 1 not exist!", exception.getMessage());
    }
}