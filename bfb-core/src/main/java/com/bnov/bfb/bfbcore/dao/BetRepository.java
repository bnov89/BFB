package com.bnov.bfb.bfbcore.dao;

import com.bnov.bfb.bfbcore.model.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository <Bet, Long> {
}
