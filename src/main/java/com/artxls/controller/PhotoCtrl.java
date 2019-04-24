package com.artxls.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.artxls.common.util.MapUtils;
import com.artxls.entity.BackPage;
import com.artxls.entity.Photo;
import com.artxls.service.PhotoService;

@RestController
@RequestMapping("photo")
public class PhotoCtrl {

	@Autowired
	private Config config;
	
	@Autowired
	private PhotoService photoServ;
	
	
	
	@GetMapping("years")
	public ResponseEntity distinctYears(@RequestParam Integer wtype) {
		return ResponseEntityManager.buildSuccess(photoServ.selectDistinctYears(wtype));
	}
	
	
	
	
	@GetMapping("detail")
	public ResponseEntity get(@RequestParam Integer photoID) {
		return ResponseEntityManager.buildSuccess(photoServ.get(photoID));
	}
	
	
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
	
	@BackEnd
	@GetMapping("delete")
	public ResponseEntity delete(Integer id) {
		photoServ.delete(id);
		return ResponseEntityManager.buildEmptySuccess();
	}
	

	@GetMapping("listback")
	public ResponseEntity listback(@RequestParam Integer pageNum,
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
		if(!StringUtils.isEmpty(name))
			name = "%" + name + "%";
		
		BackPage page = BackPage.generatePage(pageNum, pageSize);
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put("photoList", photoServ.list(infoId, ntype,subType, page,beginYear,endYear,name));
		map.put("pageInfo", page);
		
		return ResponseEntityManager.buildSuccess(map);
		
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
		
		BackPage page = BackPage.generatePage(pageNum, pageSize);
		
		return ResponseEntityManager.buildSuccess(photoServ.list(infoId, ntype,subType, page,beginYear,endYear,name));
		
	}
}
