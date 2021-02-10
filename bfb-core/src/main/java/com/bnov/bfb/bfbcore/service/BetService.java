package com.bnov.bfb.bfbcore.service;

import com.bnov.bfb.bfbcore.dao.BetRepository;
import com.bnov.bfb.bfbcore.dao.MatchRepository;
import com.bnov.bfb.bfbcore.model.MatchEntity;
import com.bnov.bfb.bfbcore.service.model.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {

    private final MatchRepository matchRepository;
    private final BetRepository betRepository;

    @Autowired
    public BetService(MatchRepository matchRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
    }

    public Bet addBet(Bet bet) {
        com.bnov.bfb.bfbcore.model.Bet betToSave = new com.bnov.bfb.bfbcore.model.Bet();
        betToSave.setAwayScore(bet.getAwayScore());
        betToSave.setHomeScore(bet.getAwayScore());
//        betToSave.setMatch();
        MatchEntity matchEntity = matchRepository.findById(bet.getMatchId()).get();
        List<com.bnov.bfb.bfbcore.model.Bet> bets = Optional.ofNullable(matchEntity.getBets())
                .map(l -> addBetToList(l, betToSave))
                .orElseGet(() -> List.of(betToSave));
        matchEntity.setBets(bets);
        matchRepository.save(matchEntity);
//        com.bnov.bfb.bfbcore.model.Bet save = betRepository.save(betToSave);
        return new Bet();
    }

    private List<com.bnov.bfb.bfbcore.model.Bet> addBetToList(List<com.bnov.bfb.bfbcore.model.Bet> bets, com.bnov.bfb.bfbcore.model.Bet betToAdd) {
        if (bets != null) {
            bets.add(betToAdd);
            return bets;
        } else {
            return List.of(betToAdd);
        }
    }
}
