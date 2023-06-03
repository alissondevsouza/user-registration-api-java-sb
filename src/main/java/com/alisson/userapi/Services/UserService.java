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

    public User create(User user) {
        User newUser = new User(
                null, user.getUserName(), user.getUserEmail(), user.getUserLogin(), user.getUserPassword()
        );

        return userRepository.save(newUser);
    }

    public User update (Long id, User user) {
        User oldUser = findById(id);

        oldUser.setUserEmail(user.getUserEmail());
        oldUser.setUserLogin(user.getUserLogin());
        oldUser.setUserName(user.getUserName());
        oldUser.setUserPassword(user.getUserPassword());

        return userRepository.save(oldUser);
    }

    public void delete (Long id) {

        userRepository.deleteById(id);
    }
}
