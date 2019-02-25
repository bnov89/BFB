package com.bnov.bfb.bfbcore.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AuthenticationProperties.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private AuthenticationProperties properties;

    @Autowired
    UserDetailsServiceImpl(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (properties.getLogin().equals(login)) {
            return new UserDetailsImpl(properties.getLogin(), properties.getPassword());
        } else {
            throw new UsernameNotFoundException("User with given login not found!");
        }
    }

}
