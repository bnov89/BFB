package com.bnov.bfb.bfbcore.service.model;

public class Bet {

    private Long id;
    private Long matchId;
    private Integer homeScore;
    private Integer awayScore;

    public Bet() {
    }

    public Bet(Long id, Long matchId, Integer homeScore, Integer awayScore) {
        this.id = id;
        this.matchId = matchId;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
