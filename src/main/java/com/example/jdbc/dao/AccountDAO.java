package com.example.jdbc.dao;

import co.paralleluniverse.fibers.SuspendExecution;
import com.example.jdbc.model.Account;
import io.dropwizard.db.ManagedDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO implements IAccountDAO {
    final private ManagedDataSource ds;

    final private String tableName = "account".toUpperCase();

    public AccountDAO (ManagedDataSource ds)
    {
        this.ds = ds;
    }


    public void createAccountTable() throws SQLException, SuspendExecution {
        try (Connection conn = ds.getConnection();
             PreparedStatement cs = conn.prepareStatement("create table " + tableName +
                     " (id int generated always as identity, name varchar(100))")
        )
        {
            cs.execute();
        }
    }

    public boolean tableExists() throws SQLException, SuspendExecution{
        try (Connection conn = ds.getConnection();
        )
        {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, tableName.toUpperCase(), null);
            boolean hasTable = rs.next();
            return hasTable;
        }
    }

    public int insertAccount(String name) throws SQLException, SuspendExecution{
//        Statement stmt = conn.createStatement();
//        stmt.execute(
//                "INSERT INTO TABLE1 (C11) VALUES (1)",
//                Statement.RETURN_GENERATED_KEYS);
//        ResultSet rs = stmt.getGeneratedKeys();
//        Code fragment 2:
        //insert into greetings(ch) values ('bonjour');
        String cmd = "insert into " + tableName + "(NAME) values ('" + name + "')";
        try (Connection conn = ds.getConnection();
             Statement s = conn.createStatement()
        )
        {

            s.executeUpdate(cmd, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs =  s.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }

    public Account getAccount(String name) throws SQLException, SuspendExecution{
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("select id, name from " + tableName + " where name = '" + name + "'")
        )
        {

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Account acct = new Account();
                acct.setId(rs.getInt(1));
                acct.setName(rs.getString(2));
            }
                return new Account();
        }
    }

    public void dropTable() throws SQLException, SuspendExecution {
        try (Connection conn = ds.getConnection();
             PreparedStatement cs = conn.prepareStatement("drop table " + tableName +"")
        )
        {
            cs.execute();
        }
    }

    public List<Account> getAccounts () throws SQLException, SuspendExecution{
        List<Account> accounts = new ArrayList<Account>();
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from " + tableName)
        )
        {

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                accounts.add(new Account(rs.getInt(1), rs.getString(2)));
            }
            return accounts;
        }
    }


}
