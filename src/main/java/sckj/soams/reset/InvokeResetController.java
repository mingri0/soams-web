package sckj.soams.reset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import sckj.soams.Constants;
import sckj.soams.bean.EchartData;
import sckj.soams.bean.InvokeBean;
import sckj.soams.bean.PageBean;
import sckj.soams.bean.Series;
import sckj.soams.bean.UiTreeGrid;
import sckj.soams.service.InvokeChainService;
import sckj.soams.service.ServiceUtils;

@RestController
public class InvokeResetController {

	@Autowired
	private InvokeChainService service;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/invokemain")
	public UiTreeGrid invokeList(PageBean pb){
		pb.setEnd(10);
		List<InvokeBean> idList = service.getInvokeMain(pb);
		UiTreeGrid grid = new UiTreeGrid(idList);
		return grid;
	}
	
	@RequestMapping("/invokeslow")
	public List<InvokeBean> invokeSlowList(){
		PageBean pb = new PageBean();
		pb.setEnd(5);
		List<InvokeBean> idList = service.getInvokeMain(pb);
		return idList;
	}
	
	/**
	 * 将子节点直接组织成树结构返回
	 * @param id
	 * @return
	 */
	@RequestMapping("/invokedetail")
	public List<InvokeBean> invokedetail(String id){
		List<InvokeBean> idList = service.getInvokeDetail(id.replace("i_", ""));
		return idList;
	}
	
	/**
	 * 指定时间段内的异常调用链占比
	 * @throws ParseException 
	 */
	@RequestMapping("/invokeerrorscale")
	public EchartData invokeErrorScale(String type) throws ParseException{
		EchartData data = null;
		//先获取最后收到调用链数据的日期
		String icdt = service.getLastInvokeChain();
		if(StringUtils.isNotBlank(icdt)){
			List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"总调用次数","异常调用次数"}));//数据分组
			List<String> categorys = ServiceUtils.getCategory(icdt,"1");
			List<Series> series = new ArrayList<Series>();//纵坐标
			List<Integer> seriesdata1 = new ArrayList<Integer>();
			List<Integer> seriesdata2 = new ArrayList<Integer>();
			int i = 0;
			for(i=0;i<categorys.size();i++){
				String category = categorys.get(i);
				seriesdata1.add(service.getInvokeCount(category));
				seriesdata2.add(service.getInvokeErrorCount(category));
				categorys.set(i, category.substring(12,16));
			}
			series.add(new Series("总调用次数", "line", seriesdata1));
			
			series.add(new Series("异常调用次数", "line",seriesdata2));
			data=new EchartData(legend, categorys, series);
		}
		return data;
	}
}
