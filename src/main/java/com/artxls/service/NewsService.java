package com.artxls.service;

import java.util.List;

import com.artxls.entity.News;

public interface NewsService {

	void add(News news);
	
	/**
	 * 
	 * @param InfoId
	 * @param ntype
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<News> list(Integer infoId,Integer ntype,Integer pageNum,Integer pageSize);
	
	void remove(Integer newsId);
	
	void update(News news);
	
}
