package com.study.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {

	@GetMapping({ "/", "/index" })
	public String index(HttpServletRequest request) {
		return request.toString() + "\tOK";
	}
	
//	@Secured(value = {"ROLE_USER"})
	@PreAuthorize("hasRole('USER')")
	@GetMapping({ "/root" })
	public String root(HttpServletRequest request) {
		return request.toString() + "\troot";
	}
	
	
}
