package com.alisson.userapi.Repositories;

import com.alisson.userapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.userEmail =:userEmail")
    User findByUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT u FROM User u WHERE u.userLogin =:userLogin")
    User findByUserLogin(@Param("userLogin") String userLogin);

}
