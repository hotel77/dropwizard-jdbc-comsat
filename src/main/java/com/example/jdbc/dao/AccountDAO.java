package com.example.jdbc.dao;

import co.paralleluniverse.fibers.SuspendExecution;
import io.dropwizard.db.ManagedDataSource;

import java.sql.*;


public class AccountDAO implements IAccountDAO {
    final private ManagedDataSource ds;

    final private String tableName = "account";

    public AccountDAO (ManagedDataSource ds)
    {
        this.ds = ds;
    }


    public void createAccountTable() throws SQLException, SuspendExecution {
        try (Connection conn = ds.getConnection();
             PreparedStatement cs = conn.prepareStatement("create table " + tableName +
                     " (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(100))")
        )
        {
            cs.execute();
        }
    }

    public boolean tableExists() throws SQLException, SuspendExecution{
        try (Connection conn = ds.getConnection();
             PreparedStatement cs = conn.prepareStatement("select OBJECT_ID('" + tableName + "')");
             ResultSet rs = cs.executeQuery();
        )
        {
                rs.next();
                return (rs.getString(1) == null);
        }
    }

    public int insertAccount(String name) throws SQLException, SuspendExecution{
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into " + tableName + " (name) values ('" + name + "')");
        )
        {
            ps.executeUpdate();
            ResultSet rs =  ps.getGeneratedKeys();
            return rs.getInt(1);
        }
    }

}
