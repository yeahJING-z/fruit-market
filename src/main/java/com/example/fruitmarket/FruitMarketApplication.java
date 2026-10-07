package com.example.fruitmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.fruitmarket.mapper")
public class FruitMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitMarketApplication.class, args);
    }
}