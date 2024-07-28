package com.eda.api.event.db.config;

import com.eda.api.event.service.EventEntity;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class MongoConfig {

    private static final String MONGO_DB_NAME = "event";
    private static final String ADMIN_DB = "admin";
    @Value("${mongo.connectionString:mongodb://localhost:27017}")
    private String connectionString;

    @Value("${mongo.databaseName:event}")
    private String dbName;

    @Autowired
    MongoDbConnection vaultConnection;

    @Bean
    public MongoClient mongoClient(){

        ConnectionString connection = new ConnectionString(connectionString);
        CodecRegistry codecRegistryPojo = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),codecRegistryPojo);

        MongoClientSettings.Builder settingBuilder = MongoClientSettings.builder()
                .applyConnectionString(connection)
                .codecRegistry(codecRegistry);
		System.out.println("------- vault creds ---------");
		System.out.println(vaultConnection);
        if(Objects.nonNull(vaultConnection.getUsername()) && Objects.nonNull(vaultConnection.getPassword())){
            settingBuilder = settingBuilder.credential(MongoCredential.
                    createCredential(vaultConnection.getUsername(), ADMIN_DB,vaultConnection.getPassword().toCharArray()));
        }

        MongoClientSettings clientSettings = settingBuilder.build();
        return MongoClients.create(clientSettings);
    }

    @Bean
    public MongoDatabase mongoDatabase(){
        return mongoClient().getDatabase(dbName);
    }

    @Bean
    public MongoCollection<EventEntity>  eventEntityMongoCollection(){
        return mongoDatabase().getCollection("events",EventEntity.class);
    }
}
