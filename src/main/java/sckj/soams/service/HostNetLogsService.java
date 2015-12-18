package sckj.soams.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.entity.HostNetLogs;
import sckj.soams.mapping.HostNetLogsMapper;

@Service
public class HostNetLogsService {
	
	@Autowired
	private HostNetLogsMapper mapper;
	
	public List<HostNetLogs> getNetLogs(String hostid,int size){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hostid", hostid);
		map.put("size", size);
		return mapper.getNetLogs(map);
	}
}
