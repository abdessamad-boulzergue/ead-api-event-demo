package com.eda.api.event;

import com.eda.api.event.config.VaultConfig;
import com.eda.api.event.db.mongo.config.MongoDbConnection;
import com.eda.api.event.db.pg.TestRepo;
import com.eda.api.event.domain.model.LogEvent;
import com.eda.api.event.db.mongo.service.SearchMongoCollection;
import com.eda.kafka.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
@EnableKafka
public class EventApplication implements CommandLineRunner {

  private final MongoDbConnection mongoDbConnection;
  private final VaultConfig configuration;

  @Value("${api-token}")
  private String appToken;
  @Autowired
  TestRepo testRepo;
  @Autowired
  DataSource dataSource;

  @Autowired
  EventProducer<String, LogEvent>  eventProducer;

  @Value("${appName}")
  private String appName;

  public EventApplication(MongoDbConnection mongoDbConnection, VaultConfig configuration) {
      this.mongoDbConnection = mongoDbConnection;
      this.configuration = configuration;
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
    System.out.println("   appName is " + appName);
    System.out.println("   dataSource is " + dataSource);
    System.out.println("   appToken is " + appToken);
    System.out.println("----------------------------------------");

    ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    while (true){
      System.out.println("while");

      LogEvent event = new LogEvent()
              .setEventId("1")
              .setEventType("run");
      eventProducer.send(event);

      executorService.submit(()->{
        System.out.println(testRepo.findAll());
      });
      try {
        Thread.sleep(30000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }


  }

}
