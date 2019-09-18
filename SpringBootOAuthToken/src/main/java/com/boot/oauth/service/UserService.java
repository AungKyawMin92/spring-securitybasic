package com.boot.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.oauth.model.UserEntity;
import com.boot.oauth.repo.UserRepo;

@Service
public class UserService {

	@Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getUserName()));
        repo.save(user);
    }
}
