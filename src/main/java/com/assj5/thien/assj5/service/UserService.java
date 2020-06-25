package com.assj5.thien.assj5.service;

import com.assj5.thien.assj5.model.BillDetail;
import com.assj5.thien.assj5.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByEmail(String email);

    User findByID(Long Id);

    void save(User user);

    void update(User user);

    void delete(Long Id);
}
