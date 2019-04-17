package com.artxls.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artxls.common.annotation.BackEnd;
import com.artxls.common.bean.Config;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.common.util.MapUtils;
import com.artxls.entity.BackPage;
import com.artxls.entity.News;
import com.artxls.service.NewsService;

@RestController
@RequestMapping("news")
public class NewsCtrl {

	@Autowired
	private Config config;
	
	@Autowired
	private NewsService newsServ;
	
	@BackEnd
	@PostMapping("add")
	public ResponseEntity add(News news) {
		newsServ.add(news);
		return ResponseEntityManager.buildEmptySuccess();
	}
	@BackEnd
	@PostMapping("update")
	public ResponseEntity update(News news) {
		newsServ.update(news);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@GetMapping("listback")
	public ResponseEntity listback(
			Integer pageNum,
			@RequestParam(required = false)Integer ntype,
			@RequestParam(required = false)Integer pageSize,
			@RequestParam(required = false)Integer infoId) {
		if(infoId == null)
			infoId = config.infoId;
		if(pageSize == null)
			pageSize = config.pageSize;
		
		BackPage page = BackPage.generatePage(pageNum, pageSize);
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put("newsList", newsServ.list(infoId, ntype, page));
		map.put("pageInfo", page);
		
		return ResponseEntityManager.buildSuccess(map);
	}
	
	
	@GetMapping("list")
	public ResponseEntity list(
			Integer pageNum,
			@RequestParam(required = false)Integer ntype,
			@RequestParam(required = false)Integer pageSize,
			@RequestParam(required = false)Integer infoId) {
		if(infoId == null)
			infoId = config.infoId;
		if(pageSize == null)
			pageSize = config.pageSize;
		
		BackPage page = BackPage.generatePage(pageNum, pageSize);
		return ResponseEntityManager.buildSuccess(newsServ.list(infoId, ntype, page));
	}
}
