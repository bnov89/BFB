package com.bnov.bfb.bfbcore.service.model;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private Long id;
    private Team home;
    private Team away;
    private Integer homeGoals;
    private Integer awayGoals;
    private List<Bet> bets = new ArrayList<>();

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Match() {
    }

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Match(Long id, Team home, Team away) {
        this.id = id;
        this.home = home;
        this.away = away;
    }

    public Match(Long id, Team home, Team away, List<Bet> bets) {
        this.id = id;
        this.home = home;
        this.away = away;
        if (bets != null) {
            this.bets.addAll(bets);
        }
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
