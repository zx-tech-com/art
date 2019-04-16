package com.artxls.common.bean;


/**
 * 从config.properties中获取
 * @author Daryl
 *
 */
public class Config {

	public final Integer pageSize;
	public final Integer infoId;
	
	
	public Config(Integer pageSize, Integer infoId) {
		super();
		this.pageSize = pageSize;
		this.infoId = infoId;
	}
}
