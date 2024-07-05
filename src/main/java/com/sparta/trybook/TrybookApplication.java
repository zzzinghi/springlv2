package com.sparta.trybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//이거 3개 고치니깐 실행됨...
@SpringBootApplication
@EntityScan(basePackages = "com.sparta.trybook.entity")
@EnableJpaRepositories(basePackages = "com.sparta.trybook.entity")
public class TrybookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrybookApplication.class, args);
    }

}
