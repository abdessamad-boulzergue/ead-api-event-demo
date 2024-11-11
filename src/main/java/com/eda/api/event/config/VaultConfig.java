package com.eda.api.event.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.vault.config.databases.VaultDatabaseProperties;
import org.springframework.vault.annotation.VaultPropertySource;

/**
 * @author Mark Paluch
 */
@ConfigurationProperties("example")
@VaultPropertySource(value = "ibmsid/712/sid-ec646")
@VaultPropertySource(value = "ibmsid/612/sid-ec647")
@EnableConfigurationProperties(VaultDatabaseProperties.class)
public class VaultConfig {

  private String username;

  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}