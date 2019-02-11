package com.bnov.bfb.bfbweb;


import com.bnov.bfb.bfbcore.authentication.UserDetailsImpl;
import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Value("${bfb.security.core.rest.login}")
    private String REST_SERVICES_USER;
    @Value("${bfb.security.core.rest.password}")
    private String REST_SERVICES_PASSWORD;
    private String USER_REST_SERVICE_URI = "http://localhost:8080/";


    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        RestTemplate template = new RestTemplateBuilder()
                .basicAuthorization(REST_SERVICES_USER, REST_SERVICES_PASSWORD)
                .build();
        HashMap<Object, Object> params = new HashMap<>();
        params.put("login", login);
        User foundUser = template
                .getForEntity(USER_REST_SERVICE_URI + "user/login/{login}", User.class, login).getBody();
        return new UserDetailsImpl(foundUser.getLogin(), foundUser.getPassword());
    }

}
