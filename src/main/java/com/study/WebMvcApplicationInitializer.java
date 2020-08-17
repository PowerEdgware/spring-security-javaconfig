package com.study;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.study.config.WebMvcConfig;
import com.study.config.WebSecurityConfig;

public class WebMvcApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		 return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//TODO 8/15/22:46
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter=
				new CharacterEncodingFilter("UTF-8");
//		Arrays.asList(characterEncodingFilter).toArray(a)
		return new Filter[] {characterEncodingFilter};
	}

}
