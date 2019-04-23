package com.artxls.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artxls.common.bean.Config;
import com.artxls.dao.MilestoneMapper;
import com.artxls.entity.Milestone;
import com.artxls.entity.MilestoneExample;
import com.artxls.service.MilestoneService;
import com.github.pagehelper.PageHelper;

@Service
public class MilestoneServiceImpl implements MilestoneService{

	@Autowired
	private MilestoneMapper milestoneMapper;
	@Autowired
	private Config config;
	
	
	@Override
	public void add(Milestone event) {
		event.setCreateTime(new Date());
		milestoneMapper.insert(event);
	}

	@Override
	public void addBatch(List<Milestone> events) {
		milestoneMapper.batchInsert(events);
	}

	@Override
	public List<Milestone> list(Integer infoId,Integer pageNum,Integer pageSize) {
		
		PageHelper.orderBy(" myear DESC");
		if(pageNum != null) {
			pageSize = pageSize == null? config.pageSize : pageSize;
			PageHelper.startPage(pageNum, pageSize);
		}
		MilestoneExample example = new MilestoneExample();
		example.createCriteria().andInfoIdEqualTo(infoId);
		
		return milestoneMapper.selectByExample(example);
	}

	@Override
	public void update(Milestone event) {
		milestoneMapper.updateByPrimaryKeySelective(event);
		
	}

	@Override
	public void delete(Integer id) {
		milestoneMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Milestone get(Integer id) {
		return milestoneMapper.selectByPrimaryKey(id);
	}

}
