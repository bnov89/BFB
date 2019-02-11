package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.UserRepository;
import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(final User userToAdd) {
        com.bnov.bfb.bfbcore.model.User user = new com.bnov.bfb.bfbcore.model.User(userToAdd.getLogin(), userToAdd.getPassword());
        com.bnov.bfb.bfbcore.model.User addedUser = userRepository.save(user);
        User result = new User(addedUser.getLogin(), addedUser.getPassword());
        result.setId(addedUser.getId());
        return result;
    }
}
