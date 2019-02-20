package com.bnov.bfb.bfbweb;


import com.bnov.bfb.bfbcore.authentication.UserDetailsImpl;
import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final BfbCoreRestServiceClient restClient;

    public UserDetailsServiceImpl(@Autowired BfbCoreRestServiceClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User foundUser = restClient
                .getForEntity("/user/login/{login}", User.class, login).getBody();
        return new UserDetailsImpl(foundUser.getLogin(), foundUser.getPassword());
    }

}
