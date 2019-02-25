package com.bnov.bfb.bfbweb;

import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BfbCoreRestServiceClient {

    private final RestTemplate template;

    @Autowired
    public BfbCoreRestServiceClient(RestTemplate template) {
        this.template = template;
    }

    public User addUser(User userToAdd) {
        return template.postForEntity("/user/add/", userToAdd, User.class).getBody();
    }

    public User findByLogin(String login) {
        return template.getForEntity("/user/login/{login}", User.class, login).getBody();
    }

}
