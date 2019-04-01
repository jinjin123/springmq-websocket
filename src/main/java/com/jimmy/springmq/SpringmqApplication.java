package com.jimmy.springmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration
public class SpringmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmqApplication.class, args);
    }

}
