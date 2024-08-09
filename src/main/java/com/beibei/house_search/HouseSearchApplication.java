package com.beibei.house_search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.beibei.house_search.mapper")
public class HouseSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseSearchApplication.class, args);
    }

}
