package com.artxls.common.body.rewrite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;

import com.artxls.common.token.TokenUtils;
import com.artxls.common.util.StringUtils;

/**
 * 可以方便的获取httpServletRequest的相关信息
 * @author Daryl
 */
public class RequestFacade {

	public static final String TOKEN = TokenUtils.KEY_TOKEN;
	public static final String QUERY = "query";
	public static final String REQUEST_BODY = "request-body";
	public static final String FORM_DATA = "form-data";
	
	
	
	public static String getHeader(String headerName,ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		List<String> list = headers.get(headerName);
		StringBuilder sbuilder = new StringBuilder();
		if(list == null)
			return null;
		
		for(String value : list) {
			sbuilder.append(value);
			sbuilder.append(",");
		}
		if(sbuilder.length() > 0)
			sbuilder.deleteCharAt(sbuilder.length() - 1);
		return sbuilder.toString();
	}
	
	
	public static String getQueryValue(String key,ServerHttpRequest request) {
		String token = RequestFacade.getHeader(key,request);
		if(!StringUtils.isNotBlank(token)) {
			String querys = request.getURI().getQuery();//不包含'?'
			Iterator<String> iterator = StringUtils.processQuery(querys);
			while(iterator.hasNext()) {
				String query = iterator.next();
				if(query.contains(key)) {
					return query.replace(key + "=", "");
				}
			}
		}
		return "";
	}
	
	public static String getBody(ServerHttpRequest request) {
		
		if("multipart/form-data".equalsIgnoreCase(getBodyType(request)))//如果有文件上传,则不返回数据
			return "";
		
		StringBuilder requestBody = new StringBuilder();
		try (InputStream is = request.getBody()){
			byte[] arr = new byte[1024];
			int length = 0;
			while((length = is.read(arr)) != -1) {
				requestBody.append(new String(arr,0,length,"utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return requestBody.toString();
	}

	public static String getBodyType(ServerHttpRequest request) {
		return getHeader(HttpHeaders.CONTENT_TYPE,request);
	}

}
