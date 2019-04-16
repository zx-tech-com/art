package com.artxls.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artxls.dao.NewsMapper;
import com.artxls.entity.News;
import com.artxls.entity.NewsExample;
import com.artxls.entity.NewsExample.Criteria;
import com.artxls.service.NewsService;
import com.github.pagehelper.PageHelper;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public void add(News news) {
		newsMapper.insert(news);
	}

	@Override
	public List<News> list(Integer info, Integer ntype, Integer pageNum, Integer pageSize) {

		NewsExample example = new NewsExample();
		Criteria criteria = example.createCriteria().andInfoIdEqualTo(info);
		if(ntype != null) 
			criteria.andNtypeEqualTo(ntype);
		PageHelper.orderBy(" create_time DESC");
		PageHelper.startPage(pageNum, pageSize);
		return newsMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void remove(Integer newsId) {
		newsMapper.deleteByPrimaryKey(newsId);
	}

	@Override
	public void update(News news) {
		newsMapper.updateByPrimaryKeyWithBLOBs(news);
	}
	
	
}
