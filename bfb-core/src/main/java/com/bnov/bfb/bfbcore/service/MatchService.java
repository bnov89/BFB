package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.MatchRepository;
import com.bnov.bfb.bfbcore.model.MatchEntity;
import com.bnov.bfb.bfbcore.model.TeamEntity;
import com.bnov.bfb.bfbcore.service.model.Bet;
import com.bnov.bfb.bfbcore.service.model.Match;
import com.bnov.bfb.bfbcore.service.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Long addMatch(Long homeTeamId, Long awayTeamId) {
        MatchEntity matchEntity = MatchEntity.builder()
                .home(TeamEntity.builder().id(homeTeamId).build())
                .away(TeamEntity.builder().id(awayTeamId).build())
                .build();
        return matchRepository.save(matchEntity).getId();
    }


    public List<Match> findAllMatches() {
        List<Match> result = new LinkedList<>();
        matchRepository.findAll().forEach(matchEntity -> result.add(Match.builder()
                .home(Team.builder()
                        .name(matchEntity.getHome().getName())
                        .build())
                .away(Team.builder()
                        .name(matchEntity.getAway().getName())
                        .build())
                .id(matchEntity.getId())
                .build()));
        return result;
    }

    private List<Bet> mapBets(List<com.bnov.bfb.bfbcore.model.Bet> betsToConvert) {
        List<Bet> result = new ArrayList<>();
        for (com.bnov.bfb.bfbcore.model.Bet betToConvert : betsToConvert) {
            result.add(new Bet(betToConvert.getId(), null, betToConvert.getHomeScore(), betToConvert.getAwayScore()));
        }
        return result;
    }

    public Match find(Long matchId) {
        return null;
        //        Optional<MatchEntity> byId = matchRepository.findById(matchId);
//        return new Match(byId.get().getId(), new com.bnov.bfb.bfbcore.service.model.Team(byId.get().getHome().getId(), byId.get().getHome().getName()), new com.bnov.bfb.bfbcore.service.model.Team(byId.get().getAway().getId(), byId.get().getAway().getName()));
    }
}
