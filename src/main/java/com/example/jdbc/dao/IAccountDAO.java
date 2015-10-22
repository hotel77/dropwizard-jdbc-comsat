package com.example.jdbc.dao;

import co.paralleluniverse.fibers.SuspendExecution;

import java.sql.SQLException;

public interface IAccountDAO {

    void createAccountTable() throws SQLException, SuspendExecution;
    boolean tableExists() throws SQLException, SuspendExecution;
    int insertAccount(String name) throws SQLException, SuspendExecution;



}
