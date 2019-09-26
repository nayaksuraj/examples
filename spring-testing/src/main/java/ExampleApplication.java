package com.johndeer.springtesting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ExampleApplication {

    private static final Logger LOGGER=LoggerFactory.getLogger(ExampleApplication.class);

    public static void main(String[] args){
        SpringApplication.run(ExampleApplication.class, args);

        LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
    }

    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}