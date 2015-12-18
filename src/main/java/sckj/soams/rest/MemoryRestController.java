package sckj.soams.rest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.bean.EchartData;
import sckj.soams.bean.MemorySwapBean;
import sckj.soams.bean.Series;
import sckj.soams.service.HostMemoryService;

@RestController
public class MemoryRestController {
	@Autowired
	private HostMemoryService service;
	
	@RequestMapping("/memorylogs")
	public EchartData getMemoryLogs(String hostid,String type){
		EchartData data = null;
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"Memory","Swap"}));//数据分组
		List<String> categorys = new ArrayList<String>();
		List<Series> series = new ArrayList<Series>();//纵坐标
		List<Double> seriesMemory = new ArrayList<Double>();
		List<Double> seriesSwap = new ArrayList<Double>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat df = new DecimalFormat("######0.00");   
		List<MemorySwapBean> msbList = service.getLastMemLogs(hostid,30);
		List<String> labels = new ArrayList<String>();
		int i = 0;
		String label = "%s<br/>内存使用量<br/>%sB(%s%%) of %sB<br/>Swap使用量<br/>%sB(%s%%) of %sB";
		for(i=0;i<msbList.size();i++){
			MemorySwapBean ms = msbList.get(i);
			categorys.add(sdf.format(ms.getRecdt()));
			double d1 = Double.valueOf(ms.getMem().replace("M",""))/Double.valueOf(ms.getMemtot().replace("M", ""))*100;
			seriesMemory.add(Double.valueOf(df.format(d1)));
			double d2 = Double.valueOf(ms.getSwap().replace("M",""))/Double.valueOf(ms.getSwaptot().replace("M",""))*100;
			seriesSwap.add(Double.valueOf(df.format(d2)));
			labels.add(String.format(label,sdf.format(ms.getRecdt()),
					ms.getMem(),Double.valueOf(df.format(d1)),ms.getMemtot(),
					ms.getSwap(),Double.valueOf(df.format(d2)),ms.getSwaptot()));
		}
		series.add(new Series("Memory", "line", seriesMemory));
		
		series.add(new Series("Swap", "line",seriesSwap));
		
		data=new EchartData(legend, categorys, series,labels);
		
		return data;
	}
}
