package com.artxls.common.token;

import com.artxls.common.util.ApplicationContextUtils;
import com.artxls.common.util.SerializeUtils;
import com.artxls.dao.token.IRedisDao;

public class TokenUtils {
	
	public static final String KEY_TOKEN = "token";
	
	public static boolean validToken(String token) {
		//JWTGenValid.validateToken(token);//JWT的验证
		//Redis的验证
		return JWTGenValid.validateToken(token) && redisValidateToken(token);
	}
	
	/**
	 * 查询Redis数据库,查看该token是否有效
	 * @param token
	 * @return
	 */
	private static boolean redisValidateToken(String token) {
		return false;
	}
	
	public static Integer getCustomerId() {
		IRedisDao redis = ApplicationContextUtils.popBean(IRedisDao.class);
		TokenEntity tokenEntity = SerializeUtils.unSerialize(redis.get(KEY_TOKEN).getBytes(), TokenEntity.class);
		tokenEntity.getClass();
		return tokenEntity.getId();
	}
	
}
