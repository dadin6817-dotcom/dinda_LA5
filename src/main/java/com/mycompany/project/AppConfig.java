/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author adind
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.mycompany.project")
public class AppConfig {

    private static final String URL =
            "jdbc:mysql://localhost:3306/projek?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource sumber = new DriverManagerDataSource();
        sumber.setDriverClassName("com.mysql.cj.jdbc.Driver");
        sumber.setUrl(URL);
        sumber.setUsername(USER);
        sumber.setPassword(PASSWORD);
        return sumber;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean pabrik = new LocalContainerEntityManagerFactoryBean();
        pabrik.setDataSource(dataSource);
        pabrik.setPackagesToScan("com.mycompany.project");
        pabrik.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties sifat = new Properties();
        sifat.setProperty("hibernate.show_sql", "true");
        pabrik.setJpaProperties(sifat);
        return pabrik;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public static PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}