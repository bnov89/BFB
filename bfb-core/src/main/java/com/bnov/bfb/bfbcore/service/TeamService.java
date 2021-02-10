package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.TeamRepository;
import com.bnov.bfb.bfbcore.model.TeamEntity;
import com.bnov.bfb.bfbcore.service.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Long addTeam(final String teamName) {
        TeamEntity teamEntity = TeamEntity.builder()
                .name(teamName)
                .build();
        return teamRepository.save(teamEntity).getId();
    }

    public List<Team> findAllTeams() {
        List<Team> result = new LinkedList<>();
        Iterable<TeamEntity> all = teamRepository.findAll();
        all.forEach(teamEntity -> result.add(Team.builder().id(teamEntity.getId()).name(teamEntity.getName()).build()));
        return result;
//        return List.of(new Team(1L, "Pomorzanka Sejny"));
//        return StreamSupport.stream(teamRepository.findAll().spliterator(), false).map(t -> new Team(t.getName())).collect(Collectors.toList());
    }
}
