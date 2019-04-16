package com.artxls.service.back;

import com.artxls.entity.Admin;

public interface AdminServ {
	
	Admin getAdminByUsernameAndPassword(Admin admin);
	
	Admin login(Admin admin);

	Admin getAdminByUsername(String username);
	
}
