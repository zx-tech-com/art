package com.artxls.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;

public class SerializeUtils {

	public static byte[] serialize(Object target) {

		if (target == null)
			return null;

		byte[] byteArr = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);) {
			oos.writeObject(target);
			byteArr = baos.toByteArray();
		} catch (IOException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.SERIALIZE_FAIL);
		}
		return byteArr;
	}

	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(byte[] byteArr, Class<T> clazz) {
		
		T resultObj = null;
		try(ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
				ObjectInputStream ois = new ObjectInputStream(bais);){
			resultObj = (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.UNSERIALIZE_FAIL);
		}
		
		return resultObj;
	}
}
