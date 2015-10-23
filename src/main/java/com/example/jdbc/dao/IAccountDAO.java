package com.example.jdbc.dao;

import co.paralleluniverse.fibers.SuspendExecution;
import com.example.jdbc.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDAO {

    Account getAccount(String name) throws SQLException, SuspendExecution;
    void createAccountTable() throws SQLException, SuspendExecution;
    boolean tableExists() throws SQLException, SuspendExecution;
    int insertAccount(String name) throws SQLException, SuspendExecution;
    void dropTable() throws SQLException, SuspendExecution;
    List<Account> getAccounts() throws SQLException, SuspendExecution;



}
