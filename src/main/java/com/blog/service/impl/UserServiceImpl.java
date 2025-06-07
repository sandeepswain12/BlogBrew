package com.blog.service.impl;

import com.blog.dtos.UserDto;
import com.blog.entity.User;
import com.blog.helper.AppConstants;
import com.blog.repository.UserRepo;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getByUserId(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> update(User user) {
        User existUser = userRepo.findById(user.getUserId()).orElseThrow();
        existUser.setEmail(user.getEmail());
        existUser.setName(user.getName());
        existUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepo.save(existUser));
    }

    @Override
    public void delete(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
        return user != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User with email " + email + " not found"));
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with the email " + email));
    }
}
