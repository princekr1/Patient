package com.example.patient.config.profileConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ProfileConfigProd {

    @Bean
    @Profile("prod")
    public DataSource dataSourceProd() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:mydbprodnew;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("prod");
        dataSource.setPassword("prod");

        return dataSource;
    }

    /*@Bean
    @Profile("dev")
    public DataSource dataSourceDev() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:mydbdevnew;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("dev");
        dataSource.setPassword("dev");

        return dataSource;
    }*/



}
