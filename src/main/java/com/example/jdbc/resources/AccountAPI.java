package com.example.jdbc.resources;

import co.paralleluniverse.fibers.SuspendExecution;
import com.codahale.metrics.annotation.Timed;
import com.example.jdbc.domain.IAccountDomain;
import com.example.jdbc.model.Account;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("/api/account")
public class AccountAPI {
    private final IAccountDomain accountDomain;

    public AccountAPI(IAccountDomain accountDomain)
    {
        this.accountDomain = accountDomain;
    }

    @POST
    @Timed
    public Account CreateAccount(@Valid Account account) throws SQLException, SuspendExecution {

        return accountDomain.createAccount(account.getName());
    }
}
