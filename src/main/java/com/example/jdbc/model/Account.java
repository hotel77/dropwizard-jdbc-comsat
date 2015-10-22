package com.example.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private int id;
    private String Name;

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return Name;
    }

    @JsonProperty
    public void setName(String name) {
        Name = name;
    }
}