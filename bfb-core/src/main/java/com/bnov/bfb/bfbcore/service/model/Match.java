package com.bnov.bfb.bfbcore.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;
    private Team home;
    private Team away;
    private Integer homeGoals;
    private Integer awayGoals;
    private List<Bet> bets = new ArrayList<>();

}
