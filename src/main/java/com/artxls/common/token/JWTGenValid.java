package com.artxls.common.token;

import java.util.Map;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;
import com.artxls.common.util.MapUtils;
import com.artxls.common.util.StringUtils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import net.minidev.json.JSONObject;

/**
 * app端登录后生成,验证(JWT的验证,Redis的验证不在这里)token
 * @author Daryl
 */
public class JWTGenValid {

	// 秘钥
	private final static String KEY = "fb63cf7d-385c-4b2c-b89d-3d4c8a9f";
	private final static byte[] SECRET = KEY.getBytes();

	public static String generateToken(TokenEntity token) {
		return creatToken(MapUtils.ObjectToMap(TokenEntity.class, token));
	}

	private static String creatToken(Map<String, Object> payloadMap) {
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
		Payload payload = new Payload(new JSONObject(payloadMap));
		JWSObject jwsObject = new JWSObject(jwsHeader, payload);
		JWSSigner jwsSigner;
		try {
			jwsSigner = new MACSigner(SECRET);
			jwsObject.sign(jwsSigner);
		} catch (KeyLengthException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_GENERATE_FAIL);
		} catch (JOSEException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_GENERATE_FAIL);
		}
		return jwsObject.serialize();
	}

	/**
	 * jwt验证token是否有效
	 * @param token
	 * @return
	 */
    public static boolean validateToken(String token){
    	boolean isValid = false;
    	if(!StringUtils.isNotBlank(token))
    		BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_INVALID);
        try {
			JWSObject jwsObject = JWSObject.parse(token);
			JWSVerifier jwsVerifier = new MACVerifier(SECRET);
			isValid = jwsObject.verify(jwsVerifier);
			if(!isValid)
				BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_INVALID);
		} catch (java.text.ParseException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_INVALID);
		} catch (JOSEException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.TOKEN_INVALID);
		}
        return isValid;
    }
}
