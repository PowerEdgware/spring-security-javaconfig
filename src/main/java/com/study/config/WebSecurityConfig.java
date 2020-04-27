package com.study.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		@Autowired
		UserDetailsService userDetailService;
		
		@Bean
		public PersistentTokenRepository getPersistentTokenRepository() {
			return new InMemoryTokenRepositoryImpl();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests(authReq -> {
//				authReq.anyRequest().authenticated();
//			}).formLogin()
//			.defaultSuccessUrl("/index", true).failureUrl("/login?error").and().logout();
			
			http.authorizeRequests().antMatchers("/favicon.ico").permitAll()
			.antMatchers("/css/**")
			.permitAll().antMatchers("/js/**").permitAll().antMatchers("/img/**").permitAll().and().rememberMe()
			.tokenRepository(getPersistentTokenRepository()).tokenValiditySeconds(1800)
			.userDetailsService(userDetailService);
			
			http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
			.defaultSuccessUrl("/index", true).failureUrl("/login?error").and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
			http.csrf().ignoringAntMatchers("/login", "/logout");
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
