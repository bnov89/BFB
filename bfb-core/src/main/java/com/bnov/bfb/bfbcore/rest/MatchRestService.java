package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.MatchService;
import com.bnov.bfb.bfbcore.service.model.Match;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MatchRestService {

    private final MatchService matchService;

    public MatchRestService(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping()
    public Long addMatch(@RequestBody Data data) {
        return matchService.addMatch(data.getHomeTeamId(), data.getAwayTeamId());
    }

    @GetMapping
    public List<Match> findAllMatches() {
        return matchService.findAllMatches();
    }

    @GetMapping("/{matchId}")
    public Match findById(@PathVariable("matchId") Long matchId) {
        return matchService.find(matchId);
    }

}
