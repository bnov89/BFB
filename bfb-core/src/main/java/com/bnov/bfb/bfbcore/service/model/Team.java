package com.bnov.bfb.bfbcore.service.model;

public class Team {

    private Long id;
    private String name;

    public Team() {
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
