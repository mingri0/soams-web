package sckj.soams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.entity.HostCpuInfo;
import sckj.soams.mapping.HostCpuInfoMapper;

@Service
public class HostCpuInfoService {
	
	@Autowired
	private HostCpuInfoMapper mapper;
	
	public HostCpuInfo getHostCpuInfo(String hostid){
		return mapper.selectByPrimaryKey(hostid);
	}
}
