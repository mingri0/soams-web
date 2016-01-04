package sckj.soams.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.bean.LoginBean;
import sckj.soams.entity.SysUser;
import sckj.soams.service.SysUserService;

@RestController
public class LoginRestController {
	
	@Autowired
	private SysUserService sysUserservice;
	
	@RequestMapping("/login")
	public LoginBean loginLogs(SysUser user){
		SysUser sysuser=null;
		LoginBean loginBean = new LoginBean();
		if(null != user && user.getLoginname() != null && !"".equals(user.getLoginname())){
			sysuser = sysUserservice.selectByLoginName(user.getLoginname());
		}
		if(sysuser == null){
			loginBean.setFlag(1);
		} else if(!sysuser.getPassword().equals(user.getPassword())){
			loginBean.setFlag(2);
		} else {
			loginBean.setFlag(0);
		}
		return loginBean;
	}
}
