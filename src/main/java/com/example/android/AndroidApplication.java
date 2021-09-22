package com.example.android;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.android.mapper")
public class AndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidApplication.class, args);
    }


}
