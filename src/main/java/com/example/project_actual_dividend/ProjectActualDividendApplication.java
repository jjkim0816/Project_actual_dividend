package com.example.project_actual_dividend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectActualDividendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectActualDividendApplication.class, args);
    }

}
