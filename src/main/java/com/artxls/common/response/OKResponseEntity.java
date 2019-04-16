package com.artxls.common.response;

import java.io.Serializable;

/**
 * 接口成功返回的数据
 * @author Daryl
 */
public class OKResponseEntity<T> extends ResponseEntity implements Serializable{

	private static final long serialVersionUID = 6400506387119885123L;
	
	private T data;
	
	public OKResponseEntity(T data) {
		this(ReturnCode.SUCCESS,data);
	}

	
	public OKResponseEntity(ReturnCode returnInfo, T data) {
		super(returnInfo);
		this.data = data;
	}
	
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
