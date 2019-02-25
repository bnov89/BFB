package com.bnov.bfb.bfbweb;

import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BfbCoreRestServiceClient {

    private final RestTemplate template;

    @Autowired
    public BfbCoreRestServiceClient(@Qualifier("bfbCoreClient") RestTemplate template) {
        this.template = template;
    }

    public User addUser(User userToAdd) {
        return template.postForEntity("/user/add/", userToAdd, User.class).getBody();
    }

    public User findByLogin(String login) {
        ResponseEntity<User> response = template.getForEntity
                ("/user/login/{login}", User.class, login);//.getBody();
        return response.getBody();
    }

}
