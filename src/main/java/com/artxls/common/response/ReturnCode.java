package com.artxls.common.response;

/**
 * <strong>200</strong> 返回成功<br>
 * <strong>4</strong>开头的前端的错误.<br>
 * 	&nbsp;&nbsp;<strong>0</strong>开头的非具体错误.<br>
 * 	&nbsp;&nbsp;<strong>1</strong>开头的具体错误(包括业务逻辑错误,JSR303验证错误...等)<br>
 * <strong>5</strong>开头的后端的错误.<br>
 * <strong>-1</strong>未知错误
 * @author Daryl
 *
 */
public enum ReturnCode {

	SUCCESS(200,"ok"),
	
	USERNAME_EMPTY(4101,"用户名为空"),
	USERNAME_PASSWD_NOT_MATCH(4102,"账号密码不匹配"),
	
	
	REQUEST_METHOD_NOT_SUPPORT(4005,"请求方法不匹配"),
	ARGUMENT_TYPE_MISMATCH(4099,"参数类型不匹配"),
	REQUEST_PARAMETER_MISSING(4098,"缺少参数"),

	FILE_SAVE_FAILED(584,"文件保存失败"), 
	FILE_NOT_EXIST(583,"文件不存在"), 
	FILE_EMPTY(581,"文件为空"),
	FILE_PATH_NOT_EXIST(582,"文件路径不存在"),

	SERIALIZE_FAIL(599,"序列化失败"),
	UNSERIALIZE_FAIL(598,"反序列化失败"),
	UNSUPPORTED_OPERATION(597,"不支持的操作"),
	RETURN_FIELD_ERROR(596,"ReturnField书写有误"),
	
	TOKEN_GENERATE_FAIL(501,"生成token错误"),
	TOKEN_INVALID(502,"token不合法"),
	DATA_OPERATION_ERROR(503,"数据操作失败"),
	
	UNKNOW_ERROR(-1,"未知错误"), 
	;
	
	private Integer code;
	private String message;
	
	private ReturnCode(Integer code,String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}


	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
