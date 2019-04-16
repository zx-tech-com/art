package com.artxls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artxls.common.annotation.BackEnd;
import com.artxls.common.bean.Config;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.entity.Photo;
import com.artxls.service.PhotoService;

@RestController
@RequestMapping("photo")
public class PhotoCtrl {

	@Autowired
	private Config config;
	
	@Autowired
	private PhotoService photoServ;
	
	@BackEnd
	@PostMapping("add")
	public ResponseEntity add(Photo photo,MultipartFile img) {
		photoServ.add(photo,img);
		return ResponseEntityManager.buildEmptySuccess();
	}
	@BackEnd
	@PostMapping("update")
	public ResponseEntity update(Photo photo,@RequestParam(required = false)MultipartFile img) {
		photoServ.update(photo,img);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@GetMapping("list")
	public ResponseEntity list(@RequestParam Integer pageNum,
			@RequestParam(required = false)Integer pageSize,
			@RequestParam Integer ntype,
			@RequestParam(required = false)Integer subType,
			@RequestParam(required = false)Integer beginYear,
			@RequestParam(required = false)Integer endYear,
			@RequestParam(required = false)String name,
			@RequestParam(required = false)Integer infoId) {
		if(infoId == null)
			infoId = config.infoId;
		if(pageSize == null)
			pageSize = config.pageSize;
		return ResponseEntityManager.buildSuccess(photoServ.list(infoId, ntype,subType, pageNum, pageSize,beginYear,endYear,name));
	}
}
