package com.artxls.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.artxls.common.annotation.BackEnd;

public class AccessInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(!(handler instanceof HandlerMethod))
			return true;
		
		if(InterceptorUtils.hasMethodAnnotation(handler, BackEnd.class)) {//	后台访问的接口
			if(!InterceptorUtils.sessionIsValid(request))//session无效
				return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
