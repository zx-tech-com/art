package com.artxls.service;

import java.util.List;

import com.artxls.entity.Milestone;

public interface MilestoneService {
	
	void add(Milestone event);
	
	void addBatch(List<Milestone> events);

	void update(Milestone event);

	void delete(Integer id);

	List<Milestone> list(Integer infoId,Integer pageNum,Integer pageSize);

	Milestone get(Integer id);
}
