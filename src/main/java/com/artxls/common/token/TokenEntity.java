package com.artxls.common.token;

import java.io.Serializable;

/**
 * token的实体类
 * @author Daryl
 */
public class TokenEntity implements Serializable{
	
	private static final long serialVersionUID = -1055056034195048432L;
	
	private Integer id;
	private Long generatedTime;
	
	public TokenEntity() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(Long generatedTime) {
		this.generatedTime = generatedTime;
	}
	
	@Override
	public String toString() {
		return "TokenEntity [id=" + id + ", generatedTime=" + generatedTime + "]";
	}
	
}
