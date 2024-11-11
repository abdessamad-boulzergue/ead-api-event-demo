package com.eda.api.event.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class LocalJwksConfig {

    // Serve the JWKS file
    @Bean
    public RouterFunction<ServerResponse> jwksRouter() {
        return route()
                .GET("/.well-known/jwks.json", request -> {
                    Resource resource = new ClassPathResource("jwks.json");
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(resource);
                })
                .build();
    }
}
