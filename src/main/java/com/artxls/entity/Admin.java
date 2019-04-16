package com.artxls.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.artxls.common.validate.Get;

public class Admin {

	private Integer id; 
	@NotBlank(message="{admin.username}",groups= {Get.class})
	private String username;
	@NotBlank(message="{admin.password}",groups= {Get.class})
	private String passwd;
	private String ctime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String password) {
		this.passwd = password;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", passwd=" + passwd + ", ctime=" + ctime + "]";
	}
	
}
