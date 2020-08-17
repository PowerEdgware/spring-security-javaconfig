package com.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class FaultController extends BaseController
{

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleError(Exception e){
		logg(e, "Internal Error");
		return ResponseEntity.ok("出错啦");
	}
}
