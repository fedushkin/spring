package com.cources.jpa.service;

import com.cources.jpa.domain.User;
import com.cources.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserName (String userName) {
        return userRepository.findByUserName(userName);
    }
}