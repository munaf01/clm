package com.inspire.clm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ClmApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClmApplication.class, args);
    }

}
