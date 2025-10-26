package com.aqi.aqiApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AqiConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
