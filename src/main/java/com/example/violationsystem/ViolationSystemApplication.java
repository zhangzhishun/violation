package com.example.violationsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.violationsystem.mapper")
@SpringBootApplication
public class ViolationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViolationSystemApplication.class, args);
    }

}
