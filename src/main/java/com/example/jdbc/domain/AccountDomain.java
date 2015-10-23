package com.example.jdbc.domain;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import com.example.jdbc.dao.IAccountDAO;
import com.example.jdbc.model.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountDomain implements IAccountDomain {

    private final IAccountDAO accountDAO;

    public AccountDomain(IAccountDAO accountDAO)
    {
        this.accountDAO = accountDAO;
    }


    @Override
    @Suspendable
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

    @Override
    @Suspendable
    public Account getAccount(String name) throws SQLException, SuspendExecution {
        if (accountDAO.tableExists())
            return accountDAO.getAccount(name);
        else return null;
    }

    @Override
    @Suspendable
    public void dropTable() throws SQLException, SuspendExecution {
        if (accountDAO.tableExists())
            accountDAO.dropTable();
    }

    @Override
    @Suspendable
    public List<Account> getAccounts() throws SQLException, SuspendExecution {
        if (accountDAO.tableExists())
            return accountDAO.getAccounts();
        else return null;
    }
}
