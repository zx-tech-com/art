package com.artxls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artxls.common.annotation.BackEnd;
import com.artxls.common.bean.Config;
import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.entity.Milestone;
import com.artxls.service.MilestoneService;

@RestController
@RequestMapping("event")
public class MilestoneCtrl {

	@Autowired
	private MilestoneService eventServ;
	@Autowired
	private Config config;
	
	@BackEnd
	@PostMapping("add")
	public ResponseEntity add(Milestone event) {
		eventServ.add(event);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@BackEnd
	@PostMapping("addBatch")
	public ResponseEntity addBatch(@RequestBody List<Milestone> events) {
		eventServ.addBatch(events);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@GetMapping("list")
	public ResponseEntity list(
			@RequestParam(required=false)Integer infoId,
			@RequestParam(required = false)Integer pageSize,
			@RequestParam(required = false)Integer pageNum) {
		if(infoId == null)
			infoId = config.infoId;
		return ResponseEntityManager.buildSuccess(eventServ.list(infoId,pageNum,pageSize));
	}
	
}
