package sckj.soams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.entity.SysUser;
import sckj.soams.mapping.SysUserMapper;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
    public SysUser selectByLoginName(String loginname){
    	SysUser user = sysUserMapper.selectByLoginName(loginname);
    	return user;
    }
}
