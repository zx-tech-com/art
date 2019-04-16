package com.artxls.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	
	public static Map<String,Object> getHashMapInstance() {
		return new HashMap<String,Object>();
	}
	
	/**
	 * 把pojo对象转成map
	 * @param obj
	 * @return
	 */
	public static<T> Map<String,Object> ObjectToMap(Class<T> clazz,T obj) {
		if(obj == null)
			return null;
		Field[] fields = clazz.getDeclaredFields();
		Map<String,Object> map = getHashMapInstance();
		for(Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
}
