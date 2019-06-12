package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.MatchService;
import com.bnov.bfb.bfbcore.service.model.Match;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchRestService {

    private static final String REST_SERVICE_PREFIX = "/match";
    private final MatchService matchService;

    public MatchRestService(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/add", method = RequestMethod.POST)
    public Match addMatch(@RequestBody final Match match) {
        return matchService.addMatch(match);
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/list")
    public List<Match> findAllMatches() {
        return matchService.findAllMatches();
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/{id}")
    public Match findById(@PathVariable("id") Long matchId) {
        return matchService.find(matchId);
    }
}
