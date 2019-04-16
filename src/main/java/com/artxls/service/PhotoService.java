package com.artxls.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artxls.entity.Photo;

public interface PhotoService {

	void add(Photo photo,MultipartFile img);
	
	List<Photo> list(Integer infoId,Integer wtype,Integer subType,Integer pageNum,Integer pageSize, Integer beginYear, Integer endYear,String name);
	
	void remove(Integer photoId);
	
	void update(Photo photo,MultipartFile img);
}
