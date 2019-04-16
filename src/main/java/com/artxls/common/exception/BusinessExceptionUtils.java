package com.artxls.common.exception;

import com.artxls.common.response.ReturnCode;

public class BusinessExceptionUtils {

	public static void throwBusinessException(ReturnCode rc) {
		throw new BusinessException(rc);
	}
	public static void throwBusinessException(Integer code) {
		throw new BusinessException(code);
	}
}
