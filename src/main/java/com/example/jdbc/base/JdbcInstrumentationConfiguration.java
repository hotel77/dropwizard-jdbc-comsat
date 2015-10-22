package com.example.jdbc.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JdbcInstrumentationConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private final HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @JsonProperty
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }


    @Valid
    @NotNull
    @JsonProperty
    private final DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return database;
    }


}