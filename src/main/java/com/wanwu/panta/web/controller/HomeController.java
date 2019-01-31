package com.wanwu.panta.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wanwu.panta.biz.IUserService;
import com.wanwu.panta.dal.domain.PantaUser;

@Controller
public class HomeController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/loginSuccess.page", method=RequestMethod.GET)
	public String loginSuccess() {
		PantaUser pu = userService.getPantaUserById(1);
		System.out.println(pu.toString());
		return "loginSuccess";
	}
	
	@RequestMapping(value="/loginFail.page", method=RequestMethod.GET)
	public String loginFail() {
		return "loginFail";
	}
	
	@RequestMapping(value="/accessDenied.page", method=RequestMethod.GET)
	public String accessDenied() {
		return "accessDenied";
	}
	
	@RequestMapping(value="/login.page", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
}
