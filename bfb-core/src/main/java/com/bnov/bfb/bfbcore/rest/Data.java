package com.bnov.bfb.bfbcore.rest;


import lombok.AllArgsConstructor;
import lombok.Builder;

@lombok.Data
@Builder
@AllArgsConstructor
public class Data {
    private Long homeTeamId;
    private Long awayTeamId;
}
