package com.eda.api.event;

import com.eda.api.event.config.VaultConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
@EnableKafka
public class EventApplication {

  public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

}
