package com.bnov.bfb.bfbcore.model;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer homeScore;
    private Integer awayScore;

    @ManyToOne
    private MatchEntity matchEntity;

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

    public MatchEntity getMatch() {
        return matchEntity;
    }

    public void setMatch(MatchEntity matchEntity) {
        this.matchEntity = matchEntity;
    }
}
