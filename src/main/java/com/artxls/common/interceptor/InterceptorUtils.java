package com.artxls.common.interceptor;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;
import com.artxls.entity.Admin;

/**
 * 	为拦截器服务的方法.
 * @author Daryl
 *
 */
public class InterceptorUtils {
	
	private static final String BACK_REQUEST = "Back-Request";

	/**
	 * 	检查hander方法是否被该注解T所修饰
	 * @param methodHandler	被检查的方法
	 * @param T		注解类型
	 * @return
	 */
	public static <T extends Annotation> boolean hasMethodAnnotation(Object methodHandler,Class<T> T) {
		
		if(methodHandler == null)
			return false;
		HandlerMethod method = (HandlerMethod)methodHandler;
		return method.hasMethodAnnotation(T);
	}
	
	/**
	 * 	所有后台发送的请求 都添加了'Back-Request':'true'请求头<br>
	 * 	其实也可以根据origin来判断.
	 * @param request
	 * @return
	 */
	public static boolean isBackEndRequest(HttpServletRequest request) {
		String flag = request.getHeader(BACK_REQUEST);
		return !StringUtils.isEmpty(flag);
	}
	
	
	public static boolean sessionIsValid(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return sessionIsValid(session);
	}
	
	public static boolean sessionIsValid(HttpSession session) {
		if(session == null)//未登录
			BusinessExceptionUtils.throwBusinessException(ReturnCode.ADMIN_NOT_LOGIN);
		if(session.getAttribute(Admin.class.getName()) == null)//session无效
			BusinessExceptionUtils.throwBusinessException(ReturnCode.SESSION_NOT_VALID);
		return true;
	}
}
