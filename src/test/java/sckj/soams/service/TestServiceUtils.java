package sckj.soams.service;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import sckj.soams.Constants;

public class TestServiceUtils {
	  
	@Test
	public void TestInvoke(){
		try {
			List<String> sList = ServiceUtils.getCategory("2015-12-08 15:34:12", Constants.CHART_INTERVAL_DAY);
			System.out.println(JSONObject.toJSONString(sList));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
