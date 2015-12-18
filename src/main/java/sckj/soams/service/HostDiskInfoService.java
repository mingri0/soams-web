package sckj.soams.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.bean.DiskInfoBean;
import sckj.soams.entity.HostDiskInfo;
import sckj.soams.mapping.HostDiskInfoMapper;

@Service
public class HostDiskInfoService {
	
	@Autowired
	private HostDiskInfoMapper mapper;
	/**
	 * 获取磁盘信息
	 * @param hostid
	 * @return
	 */
	public DiskInfoBean getDiskInfo(String hostid) {
		DiskInfoBean dib = new DiskInfoBean();
		List<HostDiskInfo> hdiList = mapper.getHostDiskInfo(hostid);
		DecimalFormat df = new DecimalFormat("######0.00");   
		double tot = 0;
		double free = 0;
		double used = 0; 
	    double usage = 0;
	    if(hdiList.size()>0){
			for (HostDiskInfo hdi : hdiList) {
				tot = tot + ServiceUtils.getRealValue(hdi.getTotal());
				free = free + ServiceUtils.getRealValue(hdi.getFree());
			}
			used = tot-free;
			usage = used/tot*100;
			dib.setTotal(tot);
			dib.setFree(free);
			dib.setUsed(used);
			dib.setUsage(Double.valueOf(df.format(usage)));
			dib.setHdiList(hdiList);
	    }
		return dib;
	}
	
	

}
