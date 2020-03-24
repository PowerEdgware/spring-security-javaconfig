package security.withoutMvc;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.study.config.WebSecurityConfig;

//TODO 和MVC互斥，否则会加载多个springSecurityFilterChain Filter导致启动报错
public class SecurityWithoutSpringMvcWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	//1. Automatically register the springSecurityFilterChain Filter for every URL in your application
	//2. Add a ContextLoaderListener that loads the WebSecurityConfig.
	public SecurityWithoutSpringMvcWebApplicationInitializer() {
		super(WebSecurityConfig.class);
	}
}
