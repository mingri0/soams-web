package sckj.soams.reset;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.bean.EchartData;

@RestController
public class CpuResetController {
	
	@RequestMapping("/cpulogs")
	public EchartData cpuInfoLogs(String type){
		EchartData ed = null;
		
		return ed;
	}
}
