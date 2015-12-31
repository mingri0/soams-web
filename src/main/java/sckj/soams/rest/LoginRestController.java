package sckj.soams.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.entity.SysUser;
import sckj.soams.service.SysUserService;

@RestController
public class LoginRestController {
	
	@Autowired
	private SysUserService sysUserservice;
	
	@RequestMapping("/login")
	public String loginLogs(SysUser user){
		SysUser sysuser=null;
		if(null != user && user.getLogginname() != null && "".equals(user.getLogginname())){
			sysuser = sysUserservice.selectByLoginName(user.getLogginname());
		}
		if(sysuser == null){
			return "{\"flag\":" + 1 + "}";
		} else if(sysuser.getPassword().equals(user.getPassword())){
			return "{\"flag\":" + 2 + "}";
		}
		return "{\"flag\":" + 0 + "}";
	}
}
