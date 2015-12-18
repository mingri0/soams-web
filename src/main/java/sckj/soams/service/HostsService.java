package sckj.soams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.entity.Hosts;
import sckj.soams.mapping.HostsMapper;

@Service
public class HostsService {
	
	@Autowired
	private HostsMapper mapper;
	
	public List<Hosts> getAllHosts(){
		return mapper.selectAll();
	}
	
	public Hosts getHostsByHostId(String hostid){
		return mapper.selectByPrimaryKey(hostid);
	}
	
}
