package com.personal.javastudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashingConfig {

    @Bean
    public String hashingAlgorithm() {
        return "SHA-256";
    }

    @Bean
    public int saltLength() {
        return 16;
    }

    @Bean
    public int hashIterations() {
        return 10000;
    }
}
