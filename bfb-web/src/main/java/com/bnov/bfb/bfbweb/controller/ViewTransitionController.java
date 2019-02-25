package com.bnov.bfb.bfbweb.controller;

import com.bnov.bfb.bfbcore.service.model.User;
import com.bnov.bfb.bfbweb.BfbCoreRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ViewTransitionController {

    private BfbCoreRestServiceClient coreClient;

    @Autowired
    public ViewTransitionController(BfbCoreRestServiceClient coreClient) {
        this.coreClient = coreClient;
    }

    @RequestMapping("/")
    public String showHomeView(Map<String, Object> model) {
        return "views/index";
    }

    @RequestMapping("/views/addUser")
    public String showAddUserView(Map<String, Object> model) {
        return "views/addUser";
    }

    @RequestMapping(value = "/action/addUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView addUser(@RequestBody MultiValueMap<String, String> formParams) {
        String login = formParams.getFirst("login");
        User addedUser = coreClient.addUser(new User(login, formParams.getFirst("password")));
        ModelAndView result = new ModelAndView("views/addUser");
        result.addObject("isUserAdded", true);
        result.addObject("userId", addedUser.getId());
        result.addObject("userLogin", addedUser.getLogin());
        return result;
    }
}
