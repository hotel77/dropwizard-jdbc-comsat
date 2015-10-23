package com.example.jdbc.domain;

import co.paralleluniverse.fibers.SuspendExecution;
import com.example.jdbc.model.Account;

import java.sql.SQLException;
import java.util.List;


public interface IAccountDomain {

    Account createAccount(String name) throws SQLException, SuspendExecution;
    Account getAccount(String name) throws SQLException, SuspendExecution;
    void dropTable() throws SQLException, SuspendExecution;
    List<Account> getAccounts() throws SQLException, SuspendExecution;
}
