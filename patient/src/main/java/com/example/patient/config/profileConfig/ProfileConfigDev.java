package com.example.patient.config.profileConfig;

import com.example.patient.entity.Contact;
import com.example.patient.service.ContactService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("dev")
public class ProfileConfigDev {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:mydbdevnew;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("dev");
        dataSource.setPassword("dev");

        return dataSource;
    }

    @Bean
    public ContactService contactService(){
        return new ContactService();
    }



}
