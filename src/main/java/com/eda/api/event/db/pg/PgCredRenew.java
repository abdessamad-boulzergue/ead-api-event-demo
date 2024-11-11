package com.eda.api.event.db.pg;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.vault.config.databases.VaultDatabaseProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.lease.SecretLeaseContainer;
import org.springframework.vault.core.lease.event.*;

import javax.sql.DataSource;

@Configuration
public class PgCredRenew {

    @Autowired
    DataSource dataSource;

    @Autowired
    VaultDatabaseProperties vaultDatabaseProperties;

    @Autowired
    public void configLease(SecretLeaseContainer leaseContainer){
        String path = vaultDatabaseProperties.getBackend().concat("/creds/").concat(vaultDatabaseProperties.getRole());
        leaseContainer.addLeaseListener(evt->{
            if (evt instanceof SecretLeaseCreatedEvent createdEvent){
                System.out.println("--- SecretLeaseCreatedEvent ---");
                String user = (String)   createdEvent.getSecrets().get("username");
                String pass = (String)   createdEvent.getSecrets().get("password");
                System.out.printf("username: %s, password: %s%n",user,pass);
                updateDataSourceCredentials(user,pass);
            } else if (evt instanceof SecretLeaseExpiredEvent expiredEvent) {
                System.out.println("--- SecretLeaseExpiredEvent ---");
                boolean isRenewable = expiredEvent.getLease().isRenewable();
                System.out.printf("leaseId: %s, isRenewable: %s%n",expiredEvent.getLease().getLeaseId(),isRenewable);
                leaseContainer.requestRenewableSecret(path);
            } else if (evt instanceof SecretLeaseErrorEvent errorEvent) {
                System.out.println("--- SecretLeaseErrorEvent ---");
                var exception = errorEvent.getException();
                System.out.printf("exception: %s%n",exception.getMessage());
            }  else if (evt instanceof AfterSecretLeaseRenewedEvent afterRenewedEvent) {
                System.out.println("--- AfterSecretLeaseRenewedEvent ---");
                var srcPath = afterRenewedEvent.getSource().getPath();
                System.out.printf("renewed path: %s%n",srcPath);
            }else if (evt instanceof BeforeSecretLeaseRevocationEvent beforeRevocationEvent) {
                System.out.println("--- BeforeSecretLeaseRevocationEvent ---");
                var srcPath = beforeRevocationEvent.getSource().getPath();
                System.out.printf("revoke path: %s%n",srcPath);
            }else if (evt instanceof SecretNotFoundEvent notFoundEvent) {
                System.out.println("--- SecretNotFoundEvent ---");
                var srcPath = notFoundEvent.getSource().getPath();
                System.out.printf("notFound path: %s%n",srcPath);
            }

        });
    }

    private void updateDataSourceCredentials(String user, String pass) {
        if(dataSource instanceof HikariDataSource hikariDataSource){
            hikariDataSource.getHikariConfigMXBean().setUsername(user);
            hikariDataSource.getHikariConfigMXBean().setPassword(pass);
            hikariDataSource.getHikariPoolMXBean().softEvictConnections();
        }
    }
}
