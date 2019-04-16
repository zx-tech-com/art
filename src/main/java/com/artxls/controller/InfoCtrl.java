package com.artxls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artxls.common.bean.Config;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.entity.Info;
import com.artxls.service.InfoService;

@RestController
@RequestMapping("info")
public class InfoCtrl {

	@Autowired
	private InfoService infoServ;
	
	@Autowired
	private Config config;
	
	@GetMapping("get")
	public ResponseEntity get() {
		Integer infoId = config.infoId;
		return ResponseEntityManager.buildSuccess(infoServ.get(infoId));
	}
	
	@PostMapping("add")
	public ResponseEntity add(Info info) {
		infoServ.add(info);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@PostMapping("update")
	public ResponseEntity update(Info info) {
		infoServ.update(info);
		return ResponseEntityManager.buildEmptySuccess();
	}
}
