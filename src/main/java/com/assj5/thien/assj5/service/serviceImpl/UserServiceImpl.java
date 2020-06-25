package com.assj5.thien.assj5.service.serviceImpl;

import com.assj5.thien.assj5.model.User;
import com.assj5.thien.assj5.repository.UserRepository;
import com.assj5.thien.assj5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByID(Long Id) {
        return userRepository.findById(Id).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
