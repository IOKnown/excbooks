package com.excbooks.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@ComponentScan(basePackages = "com.excbooks.dao")
@EnableTransactionManagement()
@EnableJpaRepositories(
        entityManagerFactoryRef = "emf",
        transactionManagerRef = "jpaTransactionManager",
        basePackages = {"com.excbooks.dao"})
@Configuration
public class DaoTestConfig {
    private static final Logger LOGGER = LogManager.getLogger(DaoTestConfig.class);

    private Properties dbConfig = new Properties();
    {
        try(InputStream fis = getClass().getClassLoader().getResourceAsStream("db_test_config.properties")){
            if(fis == null){
                throw new FileNotFoundException();
            }else {
                dbConfig.load(fis);
            }
        } catch (IOException e) {
            if( e instanceof FileNotFoundException){
                LOGGER.error("File not found", e);
            }else {
                LOGGER.error("Properties cant load", e);
            }
        }

    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/EB");
        dataSource.setUsername(dbConfig.getProperty("username"));
        dataSource.setPassword(dbConfig.getProperty("password"));
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(100);
        return  dataSource;
    }

    @Bean(name = "emf")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean  emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.excbooks.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    @Bean(name =  "jpaTransactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                entityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        properties.setProperty("spring.jpa.show","true");
        return properties;
    }

}
