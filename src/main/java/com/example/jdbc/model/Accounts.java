package com.example.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jhyatt on 10/22/2015.
 */
public class Accounts {

    List<Account> Accounts;

    @JsonProperty
    public List<Account> getAccounts() {
        return Accounts;
    }

    @JsonProperty
    public void setAccounts(List<Account> accounts) {
        Accounts = accounts;
    }


    public Accounts (List<Account> accounts)
    {
        this.Accounts = accounts;
    }
}
