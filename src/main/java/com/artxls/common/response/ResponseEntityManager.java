package com.artxls.common.response;

public class ResponseEntityManager {
	
	public static OKResponseEntity<Object> buildSuccess(Object data) {
		return new OKResponseEntity<Object>(data);
	}
	
	public static ResponseEntity buildError(ReturnCode returnInfo) {
		return new ResponseEntity(returnInfo);
	}
	
	public static ResponseEntity buildError(Integer code,String msg) {
		return new ResponseEntity(code,msg);
	}

	public static ResponseEntity buildEmptySuccess() {
		return new ResponseEntity(ReturnCode.SUCCESS);
	}
	
	
}
