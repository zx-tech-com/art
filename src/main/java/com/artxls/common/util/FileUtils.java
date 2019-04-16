package com.artxls.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;

public class FileUtils {

	private static void checkIfFileEmpty(MultipartFile file) {
		if (file == null || file.isEmpty())
			BusinessExceptionUtils.throwBusinessException(ReturnCode.FILE_EMPTY);
	}
	
	/**
	 * 通过UUID返回文件名
	 * 
	 * @param file
	 * @return
	 */
	private static String getFileName(MultipartFile file) {
		checkIfFileEmpty(file);
		return UUID.randomUUID().toString() + getFileSuffixWithDot(file);
	}

	/**
	 * 获取文件的后缀名eg：abc.jpg--->.jpg
	 * 
	 * @param file
	 * @return
	 */
	private static String getFileSuffixWithDot(MultipartFile file) {
		checkIfFileEmpty(file);
		String fileName = file.getOriginalFilename();
		String name = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
		return name;
	}

	/**
	 * 保存file到absoluteBasePath目录下
	 * 
	 * @param absoluteBasePath
	 * @param file
	 * @return 返回该文件的相对路径
	 */
	public static String saveFile(String absoluteBasePath, MultipartFile file) {
		checkIfFileEmpty(file);
		if (absoluteBasePath == null)
			BusinessExceptionUtils.throwBusinessException(ReturnCode.FILE_PATH_NOT_EXIST);
		File path = new File(absoluteBasePath);
		if (!path.exists()) {// 不存在
			path.mkdirs();// 创建
		}
		String fileName = getFileName(file);
		path = new File(absoluteBasePath + fileName);

		try {
			file.transferTo(path);
		} catch (IllegalStateException | IOException e) {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.FILE_SAVE_FAILED);
		}
		return new String(absoluteBasePath.replace(Constant.ABSOLUTE_BASE_PATH, "") + fileName).replace("\\", "/");
	}

	/**
	 * 
	 * @param path 通过实体类调用相应的getXxxUrl()返回的字符串
	 * @return
	 */
	public static boolean removeFile(String path) {
		if (path == null || path.length() == 0)// 不理会，认为删除成功
			return true;
		path = Constant.ABSOLUTE_BASE_PATH + path;
		File file = new File(path);
		if (file.exists() && !file.isDirectory())
			return file.delete();
		else {
			BusinessExceptionUtils.throwBusinessException(ReturnCode.FILE_NOT_EXIST);
			return false;
		}
	}

	public static String getFullUrl(String url) {
		return url;
	}

}
