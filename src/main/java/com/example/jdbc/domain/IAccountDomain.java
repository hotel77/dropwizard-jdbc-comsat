package com.example.jdbc.domain;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import com.example.jdbc.model.Account;

import java.sql.SQLException;
import java.util.List;


public interface IAccountDomain {

    @Suspendable
    Account createAccount(String name) throws SQLException, SuspendExecution;
    @Suspendable
    Account getAccount(String name) throws SQLException, SuspendExecution;
    @Suspendable
    void dropTable() throws SQLException, SuspendExecution;
    @Suspendable
    List<Account> getAccounts() throws SQLException, SuspendExecution;
}
