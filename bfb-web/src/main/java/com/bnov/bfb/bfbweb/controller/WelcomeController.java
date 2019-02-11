package com.bnov.bfb.bfbweb.controller;

import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class WelcomeController {
    @Value("${bfb.security.core.rest.login}")
    private String REST_SERVICES_USER;
    @Value("${bfb.security.core.rest.password}")
    private String REST_SERVICES_PASSWORD;
    private String USER_REST_SERVICE_URI = "http://localhost:8080/";

    public WelcomeController() {
    }


    @RequestMapping("/")
    public String showHomeView(Map<String, Object> model) {
        return "views/index";
    }

    @RequestMapping("/views/addUser")
    public String showAddUserView(Map<String, Object> model) {
        model.put("message", "aaa");
        return "views/addUser";
    }

    @RequestMapping(value = "/action/addUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView addUser(@RequestBody MultiValueMap<String, String> formParams) {
        String login = formParams.getFirst("login");
        RestTemplate template = new RestTemplateBuilder()
                .basicAuthorization(REST_SERVICES_USER, REST_SERVICES_PASSWORD)
                .build();
        ResponseEntity<User> addedUser = template.postForEntity(USER_REST_SERVICE_URI + "user/add/", new User(login, formParams.getFirst("password")), User.class);
        ModelAndView result = new ModelAndView("views/addUser");
        result.addObject("isUserAdded", true);
        result.addObject("userId", addedUser.getBody().getId());
        result.addObject("userLogin", addedUser.getBody().getLogin());
        return result;
    }
}
