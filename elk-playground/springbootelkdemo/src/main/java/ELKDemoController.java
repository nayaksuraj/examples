package com.springbootelkdemo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

class ELKDemoController {
    private static final Logger LOG = Logger.getLogger(ELKDemoController.class.getName());

    @Autowired
    RestTemplate restTemplete;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elk-demo")
    public String helloWorld() {
        String response = "Welcome to Spring boot ELK demo : " + new Date();
        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/elk-demo-password")
    public String elkPasswordMasking() {
        String response = "Password Masking Demo: pass123";
        LOG.log(Level.INFO, response);

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String response = "";
        try {
            throw new Exception("Exception has occured....");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            LOG.error("Exception - " + stackTrace);
            response = stackTrace;
        }

        return response;
    }
}