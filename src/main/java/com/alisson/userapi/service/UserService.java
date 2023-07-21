package com.alisson.userapi.service;

import com.alisson.userapi.repository.UserRepository;
import com.alisson.userapi.domain.Dtos.UserDto;
import com.alisson.userapi.domain.entity.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById( Long id ) {
       Optional <User> user = userRepository.findById(id);

       return user.orElseThrow( () -> new ObjectNotFoundException(
               "Objeto não encontradp! Id: ", user ));
    }

    public List <User> findAll() {

        return userRepository.findAll();
    }

    public UserDto create(User user) {
        if (findByUserName(user) != null) {
            throw new DataIntegrityViolationException("Username ja cadastrado");
        }

        if (findByUserEmail(user) != null) {
            throw new DataIntegrityViolationException("Email ja cadastrado");
        }

        if (findByUserLogin(user) != null) {
            throw new DataIntegrityViolationException("Login Name ja cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        User newUser = new User(
                null, user.getUserName(), user.getUserEmail(), user.getUserLogin(), encryptedPassword, user.getRole()
        );

        return new UserDto(userRepository.save(newUser));
    }

    public UserDto update (Long id, User user) {
        User oldUser = findById(id);

        oldUser.setUserEmail(user.getUserEmail());
        oldUser.setUserLogin(user.getUserLogin());
        oldUser.setUserName(user.getUserName());
        oldUser.setUserPassword(user.getUserPassword());

        return new UserDto(userRepository.save(oldUser));
    }

    public void delete (Long id) {

        userRepository.deleteById(id);
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
}
