package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.MatchRepository;
import com.bnov.bfb.bfbcore.model.Team;
import com.bnov.bfb.bfbcore.service.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match addMatch(Match match) {
        com.bnov.bfb.bfbcore.model.Match match1 = new com.bnov.bfb.bfbcore.model.Match(match.getId(), new Team(match.getHome().getId(), match.getHome().getName()), new Team(match.getAway().getId(), match.getAway().getName()));
        com.bnov.bfb.bfbcore.model.Match save = matchRepository.save(match1);
        return new Match(save.getId(), new com.bnov.bfb.bfbcore.service.model.Team(save.getHome().getName()), new com.bnov.bfb.bfbcore.service.model.Team(save.getAway().getName()));
    }

    public List<Match> findAllMatches() {
        Iterable<com.bnov.bfb.bfbcore.model.Match> all = matchRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .map(repoMatch -> new Match(repoMatch.getId(), new com.bnov.bfb.bfbcore.service.model.Team(repoMatch.getHome().getName()), new com.bnov.bfb.bfbcore.service.model.Team(repoMatch.getAway().getName())))
                .collect(Collectors.toList());
    }

    public Match find(Long matchId) {
        Optional<com.bnov.bfb.bfbcore.model.Match> byId = matchRepository.findById(matchId);
        return new Match(byId.get().getId(), new com.bnov.bfb.bfbcore.service.model.Team(byId.get().getHome().getId(), byId.get().getHome().getName()), new com.bnov.bfb.bfbcore.service.model.Team(byId.get().getAway().getId(), byId.get().getAway().getName()));
    }
}
