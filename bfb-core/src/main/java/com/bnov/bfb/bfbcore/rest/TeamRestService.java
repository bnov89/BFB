package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.TeamService;
import com.bnov.bfb.bfbcore.service.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TeamRestService {

    private TeamService teamService;

    @Autowired
    public TeamRestService(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public Long addTeam(@RequestBody String teamName) {
        return teamService.addTeam(teamName);
    }

    @GetMapping
    public List<Team> findAllTeams() {
        return teamService.findAllTeams();
    }

}
