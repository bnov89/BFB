package com.bnov.bfb.bfbcore.authentication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final String login;
    private final String password;

    UserDetailsServiceImpl(
            @Value("${bfb.core.authentication.login}") String login,
            @Value("${bfb.core.authentication.password}") String password) {
        this.login = login;
        this.password = password;
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
