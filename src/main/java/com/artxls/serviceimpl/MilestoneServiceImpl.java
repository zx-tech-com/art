package com.artxls.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artxls.dao.MilestoneMapper;
import com.artxls.entity.Milestone;
import com.artxls.entity.MilestoneExample;
import com.artxls.service.MilestoneService;

@Service
public class MilestoneServiceImpl implements MilestoneService{

	@Autowired
	private MilestoneMapper milestoneMapper;
	
	
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
	public List<Milestone> list(Integer InfoId) {
		
		MilestoneExample example = new MilestoneExample();
		example.createCriteria().andInfoIdEqualTo(InfoId);
		
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

}
