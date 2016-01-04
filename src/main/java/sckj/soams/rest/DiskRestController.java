package sckj.soams.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.bean.DiskInfoBean;
import sckj.soams.service.HostDiskInfoService;

@RestController
public class DiskRestController {
	
	@Autowired
	private HostDiskInfoService service;
	
	@RequestMapping("/diskinfo")
	public DiskInfoBean diskinfo(String hostid){
		return service.getDiskInfo(hostid);
	}
}
