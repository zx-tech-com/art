package com.artxls.serviceimpl.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;
import com.artxls.dao.AdminMapper;
import com.artxls.entity.Admin;
import com.artxls.service.back.AdminServ;

@Service
public class AdminServImpl implements AdminServ{

	@Autowired
	private AdminMapper mapper;
	
	
	@Override
	public Admin login(Admin admin) {
		Admin result = mapper.getAdminByUsernameAndPassword(admin);
		if(result == null)
			BusinessExceptionUtils.throwBusinessException(ReturnCode.USERNAME_PASSWD_NOT_MATCH);
		return  result;
	}
	
	
	@Override
	public Admin getAdminByUsernameAndPassword(Admin admin) {
		return mapper.getAdminByUsernameAndPassword(admin);
	}



	@Override
	public Admin getAdminByUsername(String username) {
		return mapper.getAdminByUsername(username);
	}

}
