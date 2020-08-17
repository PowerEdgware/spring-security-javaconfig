package com.study.utils;

import javax.servlet.http.HttpServletRequest;

public abstract class IPUtil {

	public static String getRemortIP(HttpServletRequest request) {
	    if (request.getHeader("x-forwarded-for") == null) {
	        return request.getRemoteAddr();
	    }
	    return request.getHeader("x-forwarded-for");
	}
}
