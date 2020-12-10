package com.demo.doubledata.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource.test1")
public class DbTest1 {
    @Value("${spring.datasource.test1.jdbc-url}")
    private String url_jdbc;

    @Value("${spring.datasource.test1.username}")
    private String username;

    @Value("${spring.datasource.test1.password}")
    private String password;
}
