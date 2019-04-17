package com.artxls.controller.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artxls.common.response.ResponseEntity;
import com.artxls.common.response.ResponseEntityManager;
import com.artxls.common.validate.Get;
import com.artxls.entity.Admin;
import com.artxls.service.back.AdminServ;

@RestController
@RequestMapping("admin")
public class AdminCtrl {

	@Autowired
	private AdminServ adminServImpl;
	
	@PostMapping(value = "login")
	public ResponseEntity login(@Validated(Get.class) Admin admin,HttpSession session,
			HttpServletRequest request){//打开cookie
		admin = adminServImpl.login(admin);
		session.setAttribute(Admin.class.getName(), admin);
		return ResponseEntityManager.buildEmptySuccess();
	}
	
	@PostMapping("logout")
	public ResponseEntity logout(HttpSession session){
		session.invalidate();
		return ResponseEntityManager.buildEmptySuccess();
	}
	
}
