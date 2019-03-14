package com.bnov.bfb.bfbweb.controller;

import com.bnov.bfb.bfbcore.service.model.Match;
import com.bnov.bfb.bfbcore.service.model.Team;
import com.bnov.bfb.bfbcore.service.model.User;
import com.bnov.bfb.bfbweb.BfbCoreRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

    @RequestMapping("/views/addMatch")
    public String showAddMatchView(Map<String, Object> model) {
        return "views/addMatch";
    }

    @RequestMapping("/views/addTeam")
    public String showAddTeamView(Map<String, Object> model) {
        return "views/addTeam";
    }

    @RequestMapping("/views/matches")
    public ModelAndView showMatchListView(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView("views/matches");
        modelAndView.addObject("matchList", coreClient.findAllMatches());
        return modelAndView;
    }

    @RequestMapping("/views/teams")
    public ModelAndView showTeamListView(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView("views/teams");
        modelAndView.addObject("teamList", coreClient.findAllTeams());
        return modelAndView;
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


    @RequestMapping(value = "/action/addMatch",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView addMatch(@RequestBody MultiValueMap<String, String> formParams, RedirectAttributes attrs) {
        String home = formParams.getFirst("home");
        String away = formParams.getFirst("away");
        coreClient.addMatch(new Match(new Team(home), new Team(away)));
        return new ModelAndView("redirect:/views/matches");
    }

    @RequestMapping(value = "/action/addTeam",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView addTeam(@RequestBody MultiValueMap<String, String> formParams, RedirectAttributes attrs) {
        String teamName = formParams.getFirst("name");
        coreClient.addTeam(new Team(teamName));
        return new ModelAndView("redirect:/views/teams");
    }

}
