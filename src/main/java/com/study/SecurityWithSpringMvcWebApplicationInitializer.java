package com.study;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//This would simply only register the springSecurityFilterChain Filter for every URL in your application.
//After that we would ensure that WebSecurityConfig was loaded in our existing ApplicationInitializer.
public class SecurityWithSpringMvcWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
