package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email =:em and u.password =:pa")
    User loginUser(@Param("em") String email, @Param("pa") String password);

    @Query("select u from User u where u.status=true")
    List<User> findAll();
    User findByEmail(String email);
}
