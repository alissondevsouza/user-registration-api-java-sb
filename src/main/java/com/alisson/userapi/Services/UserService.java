package com.alisson.userapi.Services;

import com.alisson.userapi.Repositories.UserRepository;
import com.alisson.userapi.domain.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
