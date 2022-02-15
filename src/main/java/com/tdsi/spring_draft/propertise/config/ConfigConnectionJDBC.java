package com.tdsi.spring_draft.propertise.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConfigConnectionJDBC {

    private DatabaseConfig database;

    @Autowired
    public ConfigConnectionJDBC(DatabaseConfig database) {
        this.database = database;
    }

    @PostConstruct
    public void initConnection() throws SQLException {
        DriverManager.getConnection(database.getUri(),
                database.getLogin(),
                database.getPassword());
    }
}
