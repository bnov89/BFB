package com.bnov.bfb.bfbweb.controller;

import com.bnov.bfb.bfbcore.service.model.Bet;
import com.bnov.bfb.bfbcore.service.model.Match;
import com.bnov.bfb.bfbcore.service.model.Team;
import com.bnov.bfb.bfbcore.service.model.User;
import com.bnov.bfb.bfbweb.BfbCoreRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/views/matches/{id}/update", method = RequestMethod.GET)
    public ModelAndView editMatchView(@PathVariable("id") String matchId) {
        ModelAndView modelAndView = new ModelAndView("views/addMatch");
        Match match = coreClient.findMatch(matchId);

        modelAndView.addObject("awayId", match.getAway().getId());
        modelAndView.addObject("away", match.getAway().getName());
        modelAndView.addObject("home", match.getHome().getName());
        modelAndView.addObject("homeId", match.getHome().getId());
        modelAndView.addObject("matchId", match.getId());

        return modelAndView;
    }
    @RequestMapping(value = "/views/matches/{id}/bet", method = RequestMethod.GET)
    public ModelAndView betMatchView(@PathVariable("id") String matchId) {
        ModelAndView modelAndView = new ModelAndView("views/betMatch");
        Match match = coreClient.findMatch(matchId);
        modelAndView.addObject("awayId", match.getAway().getId());
        modelAndView.addObject("away", match.getAway().getName());
        modelAndView.addObject("home", match.getHome().getName());
        modelAndView.addObject("homeId", match.getHome().getId());
        modelAndView.addObject("matchId", match.getId());

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
        String homeId = formParams.getFirst("homeId");
        String awayId = formParams.getFirst("awayId");
        String matchId = formParams.getFirst("matchId");
        Long homeLong = null, awayLong = null;
        Long matchLong = null;
        if (!StringUtils.isEmpty(homeId)) {
            homeLong = Long.valueOf(homeId);
        }
        if (!StringUtils.isEmpty(awayId)) {
            awayLong = Long.valueOf(awayId);
        }

        if (!StringUtils.isEmpty(matchId)) {
            matchLong = Long.valueOf(matchId);
        }

        coreClient.addMatch(new Match(matchLong, new Team(homeLong, home), new Team(awayLong, away)));
        return new ModelAndView("red" +
                "irect:/views/matches");
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

    @RequestMapping(value = "/action/bet",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView betMatch(@RequestBody MultiValueMap<String, String> formParams, RedirectAttributes attrs) {
        Bet bet = new Bet();

        bet.setMatchId(Long.valueOf(formParams.getFirst("matchId")));
        bet.setHomeScore(Integer.valueOf(formParams.getFirst("homeScore")));
        bet.setAwayScore(Integer.valueOf(formParams.getFirst("awayScore")));
        coreClient.addBet(bet);
        return new ModelAndView("redirect:/views/matches");
    }

}
