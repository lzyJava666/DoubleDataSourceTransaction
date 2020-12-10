package com.demo.doubledata;

import com.demo.doubledata.config.DbTest1;
import com.demo.doubledata.config.DbTest2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {DbTest1.class, DbTest2.class})
public class DoubledataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoubledataApplication.class, args);
    }

}
