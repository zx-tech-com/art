package com.artxls.common.exception;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.common.response.ReturnCode;


/**
 * 统一异常处理类.
 * @author Daryl
 *
 */
@ControllerAdvice
public class GloblalExceptionHandler {
	
	private Logger logger = LogManager.getLogger(GloblalExceptionHandler.class);
	
	/**
	 * 处理业务逻辑异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResponseEntity businessExceptionHandler(BusinessException e){
		ReturnCode rc = getReturnCode(Integer.parseInt(e.getMessage()));
		logger.error(rc.getCode() + ":" + rc.getMessage());
		ResponseEntity response = ResponseEntityManager.buildError(rc);
		return response;
	}
	
	/**
	 * 处理@RequestParam(required = true)的参数缺失异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public ResponseEntity missingServletRequestParameterHandler(MissingServletRequestParameterException e){
		ReturnCode.ARGUMENT_TYPE_MISMATCH.setMessage("缺少参数" + e.getParameterName());
		ResponseEntity response = ResponseEntityManager.buildError(ReturnCode.ARGUMENT_TYPE_MISMATCH);
		return response;
	}
	
	/**
	 * 处理参数类型不匹配异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
		ReturnCode.ARGUMENT_TYPE_MISMATCH.setMessage("参数类型不匹配" + e.getName() + " " + e.getPropertyName());
		ResponseEntity response = ResponseEntityManager.buildError(ReturnCode.ARGUMENT_TYPE_MISMATCH);
		return response;
	}
	
	/**
	 * 处理使用hibernate JSR303 验证出现的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity validateExceptionHandler(BindException e){
		ReturnCode rc = getReturnCode(getCodeFromErrors(e));
		ResponseEntity response = ResponseEntityManager.buildError(rc);
		return response;
	}
	
	/**
	 * 处理请求方法不支持异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public ResponseEntity httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
		ReturnCode.REQUEST_METHOD_NOT_SUPPORT.setMessage("请求方法不匹配 " + e.getMethod());
		ResponseEntity response = ResponseEntityManager.buildError(ReturnCode.REQUEST_METHOD_NOT_SUPPORT);
		return response;
	}
	
	/**
	 * 返回未知错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity defaultExceptionHandler(Exception e){
		ResponseEntity response = ResponseEntityManager.buildError(ReturnCode.UNKNOW_ERROR);
		System.err.println(e.getMessage());
		return response;
	}
	
	private ReturnCode getReturnCode(Integer code) {
		ReturnCode[] returnCodes = ReturnCode.values();
		for(ReturnCode returnCode : returnCodes) {
			if(returnCode.getCode().equals(code))
				return returnCode;
		}
		return ReturnCode.UNKNOW_ERROR;
	}
	
	private Integer getCodeFromErrors(BindException e) {
		Integer code = null;
		List<ObjectError> errors = e.getAllErrors();
		for(ObjectError error : errors) {
			try {
				code = Integer.parseInt(error.getDefaultMessage());
				break;
			}catch(NumberFormatException parseIntException) {
				continue;
			}
		}
		return code == null ? ReturnCode.UNKNOW_ERROR.getCode() : code;
	}
}
