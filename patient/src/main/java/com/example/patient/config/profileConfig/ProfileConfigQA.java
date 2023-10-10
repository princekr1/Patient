package com.example.patient.config.profileConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("qa")
public class ProfileConfigQA {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:mydbqanew;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("qa");
        dataSource.setPassword("qa");

        return dataSource;
    }



}
