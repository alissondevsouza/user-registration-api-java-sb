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
    @DisplayName("Should get the user by UserName successfully from the database")
    void findByUserNameCase1() {
        UserDTO createUserCase1 =
                new UserDTO(
                        1L,
                        "User Test 1",
                        "user1@gmail.com",
                        "userTest1",
                        "1234",
                        UserRole.USER
                );
        this.createUser(createUserCase1);

        User resultCase1 = this.userRepository.findByUserName(createUserCase1.getUserName());

        assertNotNull(resultCase1);
        assertEquals(createUserCase1.getUserName(), resultCase1.getUserName());
    }

    @Test
    @DisplayName("Should not get the user from the database when the UserName does not exist")
    void findByUserNameCase2() {
        UserDTO createUserCase2 =
                new UserDTO(
                        2L,
                        "User Test 2",
                        "user2@gmail.com",
                        "userTest2",
                        "1234",
                        UserRole.USER
                );

        User resultCase2 = this.userRepository.findByUserName(createUserCase2.getUserName());

        assertNull(resultCase2);
    }

    @Test
    @DisplayName("Should get the user by UserEmail successfully from the database")
    void findByUserEmailCase1() {
        UserDTO createUserCase1 =
                new UserDTO(
                        3L,
                        "User Test 3",
                        "user3@gmail.com",
                        "userTest3",
                        "1234",
                        UserRole.USER
                );
        this.createUser(createUserCase1);

        User resultCase1 = this.userRepository.findByUserEmail(createUserCase1.getUserEmail());

        assertNotNull(resultCase1);
        assertEquals(createUserCase1.getUserEmail(), resultCase1.getUserEmail());
    }

    @Test
    @DisplayName("Should not get the user from the database when the UserEmail does not exist")
    void findByUserEmailCase2() {
        UserDTO createUserCase2 =
                new UserDTO(
                        4L,
                        "User Test 4",
                        "user4@gmail.com",
                        "userTest4",
                        "1234",
                        UserRole.USER
                );

        User resultCase2 = this.userRepository.findByUserEmail(createUserCase2.getUserEmail());

        assertNull(resultCase2);
    }

    @Test
    @DisplayName("Should get the user by UserLogin successfully from the database")
    void findByUserLoginCase1() {
        UserDTO createUserCase1 =
                new UserDTO(
                        5L,
                        "User Test 5",
                        "user5@gmail.com",
                        "userTest5",
                        "1234",
                        UserRole.USER
                );
        this.createUser(createUserCase1);

        UserDetails resultCase1 = this.userRepository.findByUserLogin(createUserCase1.getUserLogin());

        assertNotNull(resultCase1);
        assertEquals(createUserCase1.getUserLogin(), resultCase1.getUsername());
    }

    @Test
    @DisplayName("Should not get the user from the database when the UserLogin does not exist")
    void findByUserLoginCase2() {
        UserDTO createUserCase2 =
                new UserDTO(
                        6L,
                        "User Test 6",
                        "user6@gmail.com",
                        "userTest6",
                        "1234",
                        UserRole.USER
                );

        UserDetails resultCase2 = this.userRepository.findByUserLogin(createUserCase2.getUserLogin());

        assertNull(resultCase2);
    }

    private void createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
    }
}