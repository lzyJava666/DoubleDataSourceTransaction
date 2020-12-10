package com.demo.doubledata.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource.test2")
public class DbTest2 {
    @Value("${spring.datasource.test2.jdbc-url}")
    private String url_jdbc;

    @Value("${spring.datasource.test2.username}")
    private String username;

    @Value("${spring.datasource.test2.password}")
    private String password;
}
