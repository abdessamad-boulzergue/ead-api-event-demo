package com.eda.api.event.config.actuator;

import com.eda.api.event.db.mongo.config.MongoConfig;
import com.eda.api.event.db.mongo.config.MongoDbConnection;
import com.mongodb.client.MongoClient;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MongoHealthIndicator extends AbstractHealthIndicator {

    private final MongoDbConnection vaultConnection;
    private final MongoClient mongoClient;
    private final MongoConfig mongoConfig;

    public MongoHealthIndicator(MongoDbConnection vaultConnection, MongoClient mongoClient, MongoConfig mongoConfig) {
        this.vaultConnection = vaultConnection;
        this.mongoClient = mongoClient;
        this.mongoConfig = mongoConfig;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if(mongoDbExists()){
            builder.up()
                    .withDetail("db", mongoConfig.getDbName())
                    .withDetail("user",vaultConnection.getUsername())
                    .build();
        }else {
            builder.down()
                    .withDetail("error","mongo database not found with name" + mongoConfig.getDbName())
                    .build();
        }
    }

    private boolean mongoDbExists() {
        if(Objects.nonNull(mongoClient) && Objects.nonNull(mongoConfig)) {
            for (String dbName : mongoClient.listDatabaseNames()) {
                if (dbName.equals(mongoConfig.getDbName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return super.getHealth(includeDetails);
    }
}
