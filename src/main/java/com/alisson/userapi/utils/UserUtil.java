package com.alisson.userapi.utils;

import com.alisson.userapi.domain.user.dto.UserDTO;
import com.alisson.userapi.exceptionHandling.exceptions.MissingParameterException;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public void isParametersNotNull(UserDTO userDTO) {
        if (userDTO.getUserName() == null || userDTO.getUserName().isEmpty()) {
            throw new MissingParameterException("User namesss is required!");
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
}
