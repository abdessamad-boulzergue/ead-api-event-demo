package com.eda.api.event;

import com.eda.api.event.db.config.MongoDbConnection;
import com.eda.api.event.service.SearchMongoCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
public class EventApplication implements CommandLineRunner {

  private final MongoDbConnection mongoDbConnection;
  private final VaultConfig configuration;
  private final SearchMongoCollection searchMongoCollection;

  public EventApplication(MongoDbConnection mongoDbConnection, VaultConfig configuration, SearchMongoCollection searchMongoCollection) {
      this.mongoDbConnection = mongoDbConnection;
      this.configuration = configuration;
      this.searchMongoCollection = searchMongoCollection;
  }

  public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

  @Override
  public void run(String... args) {

    System.out.println("----------------------------------------");
    System.out.println("Configuration properties");
    System.out.println("   example.username is  " + configuration.getUsername());
    System.out.println("   example.password is " + configuration.getPassword());
    System.out.println("----------------------------------------");

    var data = searchMongoCollection.search();

    System.out.println("data : -----");
    System.out.println(data);
    System.out.println("----- ");

  }

}
