package com.artxls.dao.token;

public interface IRedisDao{

	String get(String key);
	
	String set(String key, String value);
	
	String hget(String hkey, String key);
	
	long hset(String hkey, String key, String value);
}
