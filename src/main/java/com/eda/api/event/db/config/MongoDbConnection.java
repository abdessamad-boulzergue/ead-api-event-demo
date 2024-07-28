package com.eda.api.event.db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConnection {

    @Value("${mongodb.username:#{null}}")
    private String username;
    @Value("${mongodb.password:#{null}}")
    private String password;

    public String getUsername() {
        return username;
    }

    public MongoDbConnection setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MongoDbConnection setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "MongoDbConnection{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
