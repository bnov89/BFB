package com.bnov.bfb.bfbcore.authentication;


import com.bnov.bfb.bfbcore.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Value("${bfb.core.authentication.login}")
    private String login;
    @Value("${bfb.core.authentication.password}")
    private String password;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (this.login.equals(login)) {
            return new UserDetailsImpl(this.login, password);
        } else {
            throw new UsernameNotFoundException("User with given login not found!");
        }
    }

}
