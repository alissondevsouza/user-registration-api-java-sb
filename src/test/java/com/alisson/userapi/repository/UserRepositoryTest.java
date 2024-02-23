package com.alisson.userapi.repository;

import com.alisson.userapi.domain.user.dto.UserDTO;
import com.alisson.userapi.domain.user.entity.User;
import com.alisson.userapi.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get UserName successfully from DB")
    void findByUserNameCase1() {
        UserDTO createUser = new UserDTO(2L, "User Test 1", "user1@gmail.com", "userTest1", "1234", UserRole.USER);
        this.createUser(createUser);

        User result = this.userRepository.findByUserName(createUser.getUserName());

        assertNotNull(result);
        assertEquals(createUser.getId(), result.getId());
    }

    @Test
    @DisplayName("Should not get UserName from DB when user not exists")
    void findByUserNameCase2() {
        UserDTO createUser = new UserDTO(2L, "User Test 2", "user2@gmail.com", "userTest2", "1234", UserRole.USER);

        User result = this.userRepository.findByUserName(createUser.getUserName());

        assertNull(result);
    }

    @Test
    @DisplayName("Should get UserEmail successfully from DB")
    void findByUserEmailCase1() {
        UserDTO createUser = new UserDTO(3L, "User Test 3", "user3@gmail.com", "userTest3", "1234", UserRole.USER);
        this.createUser(createUser);

        User result = this.userRepository.findByUserEmail(createUser.getUserEmail());

        assertNotNull(result);
        assertEquals(createUser.getId(), result.getId());
    }

    @Test
    @DisplayName("Should not get UserEmail from DB when user not exists")
    void findByUserEmailCase2() {
        UserDTO createUser = new UserDTO(4L, "User Test 4", "user4@gmail.com", "userTest4", "1234", UserRole.USER);

        User result = this.userRepository.findByUserEmail(createUser.getUserEmail());

        assertNull(result);
    }

    @Test
    @DisplayName("Should get UserLogin successfully from DB")
    void findByUserLoginCase1() {
        UserDTO createUser = new UserDTO(5L, "User Test 5", "user5@gmail.com", "userTest5", "1234", UserRole.USER);
        this.createUser(createUser);

        UserDetails result = this.userRepository.findByUserLogin(createUser.getUserLogin());

        assertNotNull(result);
        assertEquals(createUser.getUserLogin(), result.getUsername());
    }

    @Test
    @DisplayName("Should not get UserLogin from DB when user not exists")
    void findByUserLoginCase2() {
        UserDTO createUser = new UserDTO(6L, "User Test 6", "user6@gmail.com", "userTest6", "1234", UserRole.USER);

        UserDetails result = this.userRepository.findByUserLogin(createUser.getUserLogin());

        assertNull(result);
    }

    private void createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
    }
}