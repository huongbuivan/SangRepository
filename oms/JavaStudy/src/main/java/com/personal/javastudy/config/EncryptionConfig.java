package com.personal.javastudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

    @Bean
    public String encryptionAlgorithm() {
        return "AES";
    }

    @Bean
    public int keySize() {
        return 128;
    }
}
