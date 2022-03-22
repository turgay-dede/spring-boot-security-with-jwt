package com.example.springbootjwt.business;

import com.example.springbootjwt.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);
    void delete(int userId);
    void update(User user);
    List<User> getAll();
    User getById(int userId);

    Optional<User> findByUsername(String username);
}
