package com.artxls.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artxls.dao.InfoMapper;
import com.artxls.entity.Info;
import com.artxls.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService{

	@Autowired
	private InfoMapper infoMapper;
	
	@Override
	public Info get(Integer infoId) {
		return infoMapper.selectByPrimaryKey(infoId);
	}

	@Override
	public void add(Info info) {
		infoMapper.insert(info);
	}

	@Override
	public void update(Info info) {
		infoMapper.updateByPrimaryKeySelective(info);
	}

}
