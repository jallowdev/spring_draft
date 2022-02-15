package com.tdsi.spring_draft.propertise.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
@Data
public class DatabaseConfig {
    private String uri;
    private String login;
    private String password;


}
