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
        String query = "Select count(*) from " + this.tableName;
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareCall(query)
        )
        {
            ps.execute();

        }
        catch (SQLException ex)
        {
            return false;
        }
        return true;
    }

    
    public int insertAccount(String name) throws SQLException, SuspendExecution{
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
