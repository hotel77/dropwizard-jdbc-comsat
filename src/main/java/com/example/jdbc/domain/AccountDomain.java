package com.example.jdbc.domain;

import co.paralleluniverse.fibers.SuspendExecution;
import com.example.jdbc.dao.IAccountDAO;
import com.example.jdbc.model.Account;

import java.sql.SQLException;

public class AccountDomain implements IAccountDomain {

    private final IAccountDAO accountDAO;

    public AccountDomain(IAccountDAO accountDAO)
    {
        this.accountDAO = accountDAO;
    }


    @Override
    public Account createAccount(String name) throws SQLException, SuspendExecution {

        if (!accountDAO.tableExists())
        {
            accountDAO.createAccountTable();
        }
        Account createdAcct = new Account();
        createdAcct.setId(accountDAO.insertAccount(name));
        createdAcct.setName(name);
        return createdAcct;
    }
}
