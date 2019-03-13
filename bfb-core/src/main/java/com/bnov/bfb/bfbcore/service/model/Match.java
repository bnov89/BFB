package com.bnov.bfb.bfbcore.service.model;

public class Match {

    private Long id;
    private Team home;
    private Team away;
    private Integer homeGoals;
    private Integer awayGoals;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }
}
