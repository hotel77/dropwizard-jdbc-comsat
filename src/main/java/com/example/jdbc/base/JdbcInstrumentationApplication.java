package com.example.jdbc.base;

import co.paralleluniverse.fibers.dropwizard.FiberApplication;
import co.paralleluniverse.fibers.dropwizard.FiberDataSourceFactory;
import com.codahale.metrics.MetricRegistry;
import com.example.jdbc.dao.AccountDAO;
import com.example.jdbc.dao.IAccountDAO;
import com.example.jdbc.domain.AccountDomain;
import com.example.jdbc.domain.IAccountDomain;
import com.example.jdbc.resources.AccountAPI;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JdbcInstrumentationApplication extends FiberApplication<JdbcInstrumentationConfiguration> {
    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) throws Exception {
        new JdbcInstrumentationApplication().run(args);
    }

    @Override
    public String getName() {
        return "JdbcInstrumentation";
    }

    @Override
    public void initialize(Bootstrap<JdbcInstrumentationConfiguration> bootstrap) {

    }


    @Override
    public void fiberRun(JdbcInstrumentationConfiguration configuration, Environment environment) throws Exception {
        applicationSetup(configuration, environment);
    }

    public void applicationSetup(JdbcInstrumentationConfiguration configuration, Environment environment) throws Exception {
        //setup the async DB
        //IMPORTANT - this database is wrapped in fibers, therefore the blocking code that is using any connections
        //inside this database must be marked @Suspendable!
        final FiberDataSourceFactory fiberDataSourceFactory = new FiberDataSourceFactory(configuration.getDatabase());
        final ManagedDataSource fiberPssiDataSource = fiberDataSourceFactory.build(metrics, "jdbc");


        //DAO objects
        final IAccountDAO accountDAO = new AccountDAO(fiberPssiDataSource);


        //Domain Objects
        final IAccountDomain accountDomain = new AccountDomain(accountDAO);

        //Resources
        final AccountAPI accountInfoAPI = new AccountAPI(accountDomain);

        //API Routes
        environment.jersey().register(accountInfoAPI);
    }



}