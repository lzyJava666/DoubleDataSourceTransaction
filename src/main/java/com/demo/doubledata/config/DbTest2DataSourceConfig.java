package com.demo.doubledata.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = "com.demo.doubledata.mapper.test2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DbTest2DataSourceConfig {
    @Primary
    @Bean(name = "db2DataSource")
    public DataSource dataSource(DbTest2 dbTest2) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(dbTest2.getUrl_jdbc());
        mysqlXADataSource.setUser(dbTest2.getUsername());
        mysqlXADataSource.setPassword(dbTest2.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("db2DataSource");
        return atomikosDataSourceBean;
    }

    @Primary
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/test2/*.xml"));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
