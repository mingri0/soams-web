package sckj.soams.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import sckj.soams.ClientInfoUtils;
import sckj.soams.bean.LoginLogsBean;
import sckj.soams.entity.LoginLogs;
import sckj.soams.entity.SysUser;
import sckj.soams.service.LoginLogsService;
import sckj.soams.service.SysUserService;

@RestController
public class LoginRestController {
	
	@Autowired
	private SysUserService sysUserservice;
	
	@Autowired
	private LoginLogsService loginLogsService;
	
	@RequestMapping("/login")
	public Integer loginLogs(SysUser user){
		SysUser sysuser=null;
		Integer flag = null;
		if(null != user && user.getLoginname() != null && !"".equals(user.getLoginname())){
			sysuser = sysUserservice.selectByLoginName(user.getLoginname());
		}
		if(sysuser == null){
			flag = 1;
		} else if(!sysuser.getPassword().equals(user.getPassword())){
			flag = 2;
		} else {
			flag = 0;
		}
		return flag;
	}
	
	@RequestMapping("/saveloginlogs")
	public Integer saveloginlogs(HttpServletRequest req){
		String agent = req.getParameter("userAgentInfo");
		String ip = ClientInfoUtils.getRemoteAddr(req);
		String loginname = req.getParameter("loginname");
		Integer userid = null;
		SysUser sysuser=null;
		Integer flag = null;
		if(null != loginname && !"".equals(loginname)){
			sysuser = sysUserservice.selectByLoginName(loginname);
			userid = sysuser.getUserid();
		}
		LoginLogs loginlogs = new LoginLogs();
		loginlogs.setLogindt(new Date());
		loginlogs.setLoginip(ip);
		loginlogs.setUserid(userid);
		loginlogs.setAgent(agent);
		flag = loginLogsService.saveloginlogs(loginlogs);
		return flag;
	}
	
	@RequestMapping("/getloginxx")
	public String getLoginLogs(HttpServletRequest req) {
		String loginname = req.getParameter("loginname");
		SysUser sysuser=null;
		String jsonstr = null;
		LoginLogsBean loginLogsBean = null;
		if(loginname != null && !"".equals(loginname)){
			sysuser = sysUserservice.selectByLoginName(loginname);
		}
		if(sysuser != null){
			loginLogsBean = loginLogsService.getLoginlogsByUserid(sysuser.getUserid());
			if(loginLogsBean != null){
				loginLogsBean.setUsername(sysuser.getUsername());
				loginLogsBean.setLoginname(sysuser.getLoginname());
				jsonstr = JSON.toJSONStringWithDateFormat(loginLogsBean, "yyyy-MM-dd HH:mm:ss",
						SerializerFeature.WriteDateUseDateFormat);
				System.out.println(jsonstr);
			}
		}
		return jsonstr;
	}
}
