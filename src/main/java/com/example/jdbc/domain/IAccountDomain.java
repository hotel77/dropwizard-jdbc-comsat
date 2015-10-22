package com.example.jdbc.domain;

import co.paralleluniverse.fibers.SuspendExecution;
import com.example.jdbc.model.Account;

import java.sql.SQLException;


public interface IAccountDomain {

    Account createAccount(String name) throws SQLException, SuspendExecution;
}
