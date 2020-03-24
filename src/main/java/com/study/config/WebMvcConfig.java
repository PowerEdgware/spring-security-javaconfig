package com.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * alternative method is extending directly from
 * {@link WebMvcConfigurationSupport} or {@link DelegatingWebMvcConfiguration}
 * 
 * @author shangcj
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.study")
public class WebMvcConfig {

	// other beans @Bean define here
}
