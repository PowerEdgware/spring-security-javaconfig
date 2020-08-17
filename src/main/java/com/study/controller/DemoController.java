package com.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class DemoController {

	@Autowired
	HttpServletRequest request;
	
//	@Secured(value = {"ROLE_USER"})
	@PreAuthorize("hasRole('USER')")
	@GetMapping({ "/root" })
	public String root(HttpServletRequest request) {
		return request.toString() + "\troot";
	}
	
	
}
