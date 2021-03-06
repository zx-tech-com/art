package com.artxls.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.artxls.entity.BackPage;
import com.artxls.entity.Photo;

public interface PhotoService {

	void add(Photo photo,MultipartFile img);
	
	List<Photo> list(Integer infoId,Integer wtype,Integer subType,BackPage page, Integer beginYear, Integer endYear,String name);
	
	void remove(Integer photoId);
	
	void update(Photo photo,MultipartFile img);

	void delete(Integer id);
	
	Photo get(Integer id);
	
	List<Map<String,Object>> selectDistinctYears(Integer ntype);
}
