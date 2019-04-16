package com.artxls.common.response;

import java.io.Serializable;

/**
 * 接口失败返回的数据
 * @author Daryl
 *
 */
public class ResponseEntity implements Serializable{

	private static final long serialVersionUID = -1856241268717199449L;
	
	private Integer code;
	private String message;
	
	public ResponseEntity() {

	}

	public ResponseEntity(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ResponseEntity(ReturnCode returnInfo) {
		this.setCode(returnInfo.getCode());
		this.setMessage(returnInfo.getMessage());
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
