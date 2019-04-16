package com.artxls.common.exception;

import com.artxls.common.response.ReturnCode;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(Integer code) {
		super(String.valueOf(code));
	}
	
	public BusinessException(ReturnCode returnInfo) {
		super(String.valueOf(returnInfo.getCode()));
	}
	
}
