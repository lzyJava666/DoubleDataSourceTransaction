package com.demo.doubledata.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;
import javax.sql.DataSource;
@MapperScan(basePackages = "com.demo.doubledata.mapper.test1", sqlSessionFactoryRef = "db1SqlSessionFactory")
@Configuration
public class DbTest1DataSourceConfig {

    @Bean(name = "db1DataSource")
    public DataSource dataSource(DbTest1 dbTest1) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(dbTest1.getUrl_jdbc());
        mysqlXADataSource.setUser(dbTest1.getUsername());
        mysqlXADataSource.setPassword(dbTest1.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("db1DataSource");

        return atomikosDataSourceBean;
    }


    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/test1/*.xml"));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }


    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
