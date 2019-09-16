package com.bnov.bfb.bfbcore.rest;

import com.bnov.bfb.bfbcore.service.BetService;
import com.bnov.bfb.bfbcore.service.model.Bet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BetRestService {

    private static final String REST_SERVICE_PREFIX = "/bet";
    private final BetService betService;

    public BetRestService(BetService betService) {
        this.betService = betService;
    }

    @RequestMapping(value = REST_SERVICE_PREFIX + "/add", method = RequestMethod.POST)
    public Bet addBet(@RequestBody final Bet bet) {
        return betService.addBet(bet);
    }

}
