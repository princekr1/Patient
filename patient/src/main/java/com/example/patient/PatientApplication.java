package com.example.patient;

import com.example.patient.config.ExternalConfig;
import com.example.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableWebMvc
@ComponentScan(basePackages={"com.example.patient"})
public class PatientApplication {

	public static final Logger logger= LoggerFactory.getLogger(PatientApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(PatientApplication.class, args);
		logger.info("Env : "+ ExternalConfig.ENV);
		logger.info("file path : :${CONF_DIR}/application-${spring.profiles.active}.properties");

	}



}
