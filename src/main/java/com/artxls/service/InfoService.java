package com.artxls.service;

import com.artxls.entity.Info;

public interface InfoService {
	
	Info get(Integer infoId);
	
	void add(Info info);
	
	void update(Info info);
}
