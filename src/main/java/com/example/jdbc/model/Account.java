package com.example.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private int id;
    private String name;

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
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    public Account (){
        id = 0;
        name = "";
    }

    public Account(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}