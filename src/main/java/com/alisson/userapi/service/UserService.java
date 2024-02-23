package com.alisson.userapi.service;

import com.alisson.userapi.domain.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDTO findById(Long userId);

    List<UserDTO> findAll();

    UserDTO create(UserDTO userDTO);

    UserDTO update(Long userId, UserDTO userDto);

    String delete(Long userId);

    String encryptedPassword(String userPassword);
}
