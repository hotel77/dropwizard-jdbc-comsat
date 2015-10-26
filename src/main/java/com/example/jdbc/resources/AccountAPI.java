package com.example.jdbc.resources;

import co.paralleluniverse.fibers.SuspendExecution;
import com.codahale.metrics.annotation.Timed;
import com.example.jdbc.domain.IAccountDomain;
import com.example.jdbc.model.Account;
import com.example.jdbc.model.Accounts;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/api/account")
@Produces(MediaType.APPLICATION_JSON)
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

    @POST
    @Timed
    @Path("/drop")

    public void dropTable(@QueryParam("name")String name) throws SQLException, SuspendExecution {

        accountDomain.dropTable();
    }

    @GET
    @Timed
    @Path("/all")

    public Accounts getAllAccounts() throws SQLException, SuspendExecution {

        return new Accounts(accountDomain.getAccounts());
    }
}
