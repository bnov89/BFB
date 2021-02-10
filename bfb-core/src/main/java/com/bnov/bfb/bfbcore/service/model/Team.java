package com.bnov.bfb.bfbcore.service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Team {
    private Long id;
    private String name;
}
