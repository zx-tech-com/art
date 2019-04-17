package com.artxls.common.body.rewrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.artxls.common.annotation.ReturnField;
import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.OKResponseEntity;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ReturnCode;
import com.artxls.common.token.TokenUtils;
import com.artxls.common.util.MapUtils;

@SuppressWarnings({"rawtypes","unchecked"})
//@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		ReturnField methodAnnotation = returnType.getMethodAnnotation(ReturnField.class);
		if(body instanceof OKResponseEntity && methodAnnotation != null) {//成功实体类
			long begin = System.currentTimeMillis();
			reWriteBody(body,methodAnnotation);
			System.err.println("重写返回数据共费时 ： " + (System.currentTimeMillis()-begin) + "ms");
		}else if(body instanceof ResponseEntity && ((ResponseEntity)body).getCode() != ReturnCode.SUCCESS.getCode()){//失败实体类,返回错误信息.
			long begin = System.currentTimeMillis();
			addParamsAndUrl(body,request);
			System.err.println("组装参数信息共费时 ： " + (System.currentTimeMillis()-begin) + "ms");
		}
		return body;
	}

	/**
	 * 请求出错,打印参数信息
	 * @param body
	 * @param request 
	 */
	private void addParamsAndUrl(Object body, ServerHttpRequest request) {
		//1.添加token信息.
		Map<String,Object> result = (Map<String,Object>)body;
		result.put(RequestFacade.TOKEN,RequestFacade.getQueryValue(TokenUtils.KEY_TOKEN,request));
		//2.添加query参数.
		result.put(RequestFacade.QUERY, request.getURI().getQuery());
		//3.添加request-body数据
		result.put(RequestFacade.REQUEST_BODY,RequestFacade.getBody(request));
	}
	

	private void reWriteBody(Object body, ReturnField methodAnnotation) {
		IFieldComponent fieldSet = assembleFieldSet(methodAnnotation);
		Object data = JSON.toJSON(((OKResponseEntity)body).getData());
		((OKResponseEntity)body).setData(reWriteField(data,fieldSet));
	}

	private IFieldComponent assembleFieldSet(ReturnField methodAnnotation) {
		String[] values = methodAnnotation.value();
		FieldPattern pattern = methodAnnotation.pattern();
		return new FieldSet(pattern,"DATA",values);
	}
	

	/**
	 * @param data
	 * @param fields
	 * @return
	 */
	private Object reWriteField(Object data, IFieldComponent fields){
		Object finalResult = null;
		System.err.println(fields.getValue()+ " : " + data);
		if(fields instanceof Field) {//如果fields本身已经是普通属性,直接返回,无需再遍历data
			return data;
		}
		
		//根据data种类分别处理
		if(data instanceof JSONArray) {//JSONArray处理,fields不发生变化
			finalResult = new ArrayList<Object>();
			JSONArray dataArr = (JSONArray)data;
			Iterator<Object> iterator = dataArr.iterator();
			while(iterator.hasNext()) {
				Object tempData = iterator.next();
				((List)finalResult).add(reWriteField(tempData,fields));
			}
		}else if(data instanceof JSONObject) {

			finalResult = initFinalResult(data,fields);
			
			Map<String,Object> map = (Map<String,Object>)data;
			Iterator<IFieldComponent> fieldSet = fields.getSubFieldComponents();
			//遍历fieldSet
			while(fieldSet.hasNext()) {
				IFieldComponent subField = fieldSet.next();
				Object value = map.get(subField.getValue());
				if(value == null) {//do nothing 没有该值
					BusinessExceptionUtils.throwBusinessException(ReturnCode.RETURN_FIELD_ERROR);
				}
				if(subField.getFieldPattern().equals(FieldPattern.INCLUDE))
					mapMergePut(((Map<String, Object>)finalResult),subField.getValue(),reWriteField(value,subField));
				else 
					((Map) finalResult).remove(subField.getValue());
			}
			
			
		}
		return finalResult;
	}
	
	
	
	
	private Object initFinalResult(Object data, IFieldComponent fields) {
		Iterator<IFieldComponent> fieldSet = fields.getSubFieldComponents();
		IFieldComponent subField = null;
		//遍历fieldSet
		while(fieldSet.hasNext()) {
			subField = fieldSet.next();
			break;
		}
		if(subField == null)//讲道理 这行代码 不可能为true
			BusinessExceptionUtils.throwBusinessException(ReturnCode.UNKNOW_ERROR);
		if(subField.getFieldPattern().equals(FieldPattern.INCLUDE)){
			return MapUtils.getHashMapInstance();
		}else
			return ((JSONObject) data).clone();
	}

	/**
	 * 	往map里添加key-value对.<br>
	 * 	若key不存在,直接添加.<br>
	 * 	若key已存在,将value和oldValue合并再添加<br>
	 * @param map
	 * @param key
	 * @param newValue
	 */
	private void mapMergePut(Map<String,Object> map,String key,Object newValue) {
		//newValue 是null 直接返回,不往map里放。
		if(newValue == null)
			return;
		Object oldValue = map.get(key);
		//尝试合并
		if(oldValue instanceof Map) {
			Map<String,Object> oldMap = (Map<String,Object>)oldValue;
			Map<String,Object> newMap = (Map<String,Object>)newValue;
			newMap.putAll(oldMap);
			
		}else if(oldValue instanceof List) {//保证顺序不可乱
			List<Object> oldArr = (List<Object>)oldValue;
			List<Object> newArr = (List<Object>)newValue;
			mergeArr(oldArr,newArr);
		}
		map.put(key, newValue);
	}

	private void mergeArr(List<Object> oldArr, List<Object> newArr) {
		if((oldArr.size() == 0)) {
			return;
		}
		if((oldArr.get(0) instanceof List)) {//属于jsonArray
			for(int i = 0; i < oldArr.size(); i++) {
				mergeArr((List<Object>)(oldArr.get(i)),(List<Object>)(newArr.get(i)));
			}
		}else if((oldArr.get(0) instanceof Map)) {
			for(int i = 0; i < oldArr.size(); i++) {
				Map<String,Object> oldMap = (Map<String,Object>)(oldArr.get(i));
				Map<String,Object> newMap = (Map<String,Object>)(newArr.get(i));
				newMap.putAll(oldMap);
			}
		}//其他情况直接用newArr替换oldArr,因此不用执行任何操作
	}
}
