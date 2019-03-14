package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.TeamService;
import com.bnov.bfb.bfbcore.service.model.Match;
import com.bnov.bfb.bfbcore.service.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamRestService {
    private static final String REST_SERVICE_PREFIX = "/team";
    private TeamService teamService;

    @Autowired
    public TeamRestService(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/add", method = RequestMethod.POST)
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/list")
    public List<Team> findAllTeams() {
        return teamService.findAllTeams();
    }

}
