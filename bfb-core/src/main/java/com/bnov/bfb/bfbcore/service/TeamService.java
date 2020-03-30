package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.TeamRepository;
import com.bnov.bfb.bfbcore.service.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team addTeam(Team team) {
        com.bnov.bfb.bfbcore.model.Team save = teamRepository.save(new com.bnov.bfb.bfbcore.model.Team(team.getName()));
        return new Team(save.getName());
    }

    public List<Team> findAllTeams() {
        return List.of(new Team(1L, "Pomorzanka Sejny"));
//        return StreamSupport.stream(teamRepository.findAll().spliterator(), false).map(t -> new Team(t.getName())).collect(Collectors.toList());
    }
}
