package com.bnov.bfb.bfbcore.authentication;


import com.bnov.bfb.bfbcore.dao.UserRepository;
import com.bnov.bfb.bfbcore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User foundUser = Optional.ofNullable(userRepository.findByLogin(login))
                .orElseThrow(() -> new UsernameNotFoundException("User with given login not found!"));
        return new UserDetailsImpl(foundUser.getLogin(), foundUser.getPassword());
    }

}
