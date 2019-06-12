package com.bnov.bfb.bfbweb;

import com.bnov.bfb.bfbcore.service.model.Match;
import com.bnov.bfb.bfbcore.service.model.Team;
import com.bnov.bfb.bfbcore.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public Match addMatch(Match matchToAdd) {
        return template.postForEntity("/match/add", matchToAdd, Match.class).getBody();
    }

    public List<Match> findAllMatches() {
        return Arrays.asList(template.getForEntity("/match/list", Match[].class, new HashMap<>()).getBody());
    }

    public Match findMatch(String id) {
        return template.getForEntity("/match/{id}", Match.class, id).getBody();
    }

    public Team addTeam(Team team) {
        return template.postForEntity("/team/add", team, Team.class).getBody();
    }

    public List<Team> findAllTeams() {
        return Arrays.asList(template.getForEntity("/team/list", Team[].class, new HashMap<>()).getBody());
    }
}
