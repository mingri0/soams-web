package sckj.soams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.bean.LoginLogsBean;
import sckj.soams.entity.LoginLogs;
import sckj.soams.mapping.LoginLogsMapper;

@Service
public class LoginLogsService {
	
	@Autowired
	private LoginLogsMapper longLogsMapper;
	
    public Integer saveloginlogs(LoginLogs loginlogs){
    	int flag = longLogsMapper.saveloginlogs(loginlogs);
    	return flag;
    }
    
    
    public LoginLogsBean getLoginlogsByUserid(Integer userid){
    	LoginLogsBean loginlogs = longLogsMapper.getLoginlogsByUserid(userid);
    	return loginlogs;
    }
}
