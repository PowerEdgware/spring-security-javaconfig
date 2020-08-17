package com.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


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
public class WebMvcConfig implements /*TODO 2020/08/15*/ WebMvcConfigurer{

	// other beans @Bean define here
	
	//TODO 2020/08/15
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		
		//add InternalResourceViewResolver
		registry.jsp("/WEB-INF/views/", ".jsp");
		//add BeanNameViewResolver
		registry.beanName();
		
		org.springframework.web.servlet.view.json.MappingJackson2JsonView jackson2JsonView=
				new MappingJackson2JsonView();
		//enable ContentNegotiatingViewResolver
		registry.enableContentNegotiation(jackson2JsonView);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
}
