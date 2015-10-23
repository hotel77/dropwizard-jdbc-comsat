package com.example.jdbc.dao;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import com.example.jdbc.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDAO {

    @Suspendable
    Account getAccount(String name) throws SQLException, SuspendExecution;
    @Suspendable
    void createAccountTable() throws SQLException, SuspendExecution;
    @Suspendable
    boolean tableExists() throws SQLException, SuspendExecution;
    @Suspendable
    int insertAccount(String name) throws SQLException, SuspendExecution;
    @Suspendable
    void dropTable() throws SQLException, SuspendExecution;
    @Suspendable
    List<Account> getAccounts() throws SQLException, SuspendExecution;



}
