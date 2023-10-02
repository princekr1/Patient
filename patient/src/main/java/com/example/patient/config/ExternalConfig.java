package com.example.patient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"file:${CONF_DIR}/application-${spring.profiles.active}.properties"})
public class ExternalConfig {

    public static final String ENV="${env}";
}
