package com.studygroupzs.virtuallibrary.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Logger.Level;

@Configuration
@EnableFeignClients(basePackages = "com.studygroupzs.virtuallibrary.client")
public class FeignClientsConfiguration {
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // Define o nível de log do Feign (exemplo: FULL)
    }
}
