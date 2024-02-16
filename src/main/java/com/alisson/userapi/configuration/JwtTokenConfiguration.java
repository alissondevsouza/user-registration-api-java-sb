package com.alisson.userapi.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt-token")
@Data
public class JwtTokenConfiguration {

    private String secret;
}
