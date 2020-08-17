package com.study.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;

	public void logg(String msg) {
		request.getServletContext().log(LocalDateTime.now() + "\t" + msg);
	}

	public void logg(Throwable ex,String msg) {
		request.getServletContext().log(LocalDateTime.now()+"\t"+msg,ex);
	}
}
