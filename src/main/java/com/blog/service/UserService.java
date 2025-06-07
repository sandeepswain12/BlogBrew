package com.blog.service;

import com.blog.dtos.UserDto;
import com.blog.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> getByUserId(String userId);

    Optional<User> update(User user);

    void delete(String userId);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);
}
