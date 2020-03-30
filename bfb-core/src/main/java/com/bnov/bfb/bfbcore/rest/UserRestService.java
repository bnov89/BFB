package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.UserService;
import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserRestService {

    private static final String REST_SERVICE_PREFIX = "/user";
    private final UserService userService;

    @Autowired
    public UserRestService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/add/", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/login/{login}")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public User findUser(@PathVariable(value = "login") String login) {
        return userService.findByLogin(login);
    }
}
