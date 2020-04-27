package com.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {

	@Autowired
	HttpServletRequest request;
	@GetMapping({ "/", "/index" })
	public String index(HttpServletRequest request) {
		String id=this.request.getSession().getId();
		return request.toString() + "\t"+id;
	}
	
//	@Secured(value = {"ROLE_USER"})
	@PreAuthorize("hasRole('USER')")
	@GetMapping({ "/root" })
	public String root(HttpServletRequest request) {
		return request.toString() + "\troot";
	}
	
	
}
