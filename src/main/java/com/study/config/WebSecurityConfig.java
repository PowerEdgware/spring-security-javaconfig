package com.study.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
		userDetailService
				.createUser(User.withDefaultPasswordEncoder().username("bob").password("passwd").roles("USER").build());
		return userDetailService;
	}

	@Configuration
	public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests(authReq -> {
				authReq.anyRequest().authenticated();
			}).formLogin();
		}
	}

	@Configuration
	@Order(2)
	static class ApiWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**").authorizeRequests(authorizeRequests -> {
				authorizeRequests.anyRequest().hasRole("USER");
			});
		}
	}
}
