package com.artxls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artxls.common.bean.Config;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.entity.News;
import com.artxls.service.NewsService;

@RestController
@RequestMapping("news")
public class NewsCtrl {

	@Autowired
	private Config config;
	
	@Autowired
	private NewsService newsServ;
	
	
	@PostMapping("add")
	public ResponseEntity add(News news) {
		newsServ.add(news);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@PostMapping("update")
	public ResponseEntity update(News news) {
		newsServ.update(news);
		return ResponseEntityManager.buildEmptySuccess();
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
		
		return ResponseEntityManager.buildSuccess(newsServ.list(infoId, ntype, pageNum, pageSize));
	}
}
