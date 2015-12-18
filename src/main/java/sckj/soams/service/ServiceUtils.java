package sckj.soams.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sckj.soams.Constants;

public class ServiceUtils {
	
	public static List<String> getCategory(String icdt,String type) throws ParseException {
		List<String> dtList = new ArrayList<String>();
		//实时的时间点
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.parse(icdt);
		if(Constants.CHART_INTERVAL_DAY.equals(type)){
			//获取当前时间点的，每1个分组的时间点
			Calendar cal = df.getCalendar();
			cal.add(Calendar.HOUR_OF_DAY, -12);;
			dtList.add(df.format(cal.getTime()));
			for(int i=1;i<=12;i++){
				cal.add(Calendar.HOUR_OF_DAY, 1);
				dtList.add(df.format(cal.getTime()).substring(0,16));
			}
		}else{
			//获取当前时间点的，每1个小时的时间点
			Calendar cal = df.getCalendar();
			cal.add(Calendar.MINUTE, -12);;
			dtList.add(df.format(cal.getTime()));
			for(int i=1;i<=12;i++){
				cal.add(Calendar.MINUTE, 1);
				dtList.add(df.format(cal.getTime()).substring(0,16));
			}
		}
		return dtList;
	}
	
	public static double getRealValue(String input){
		String val = input.replace("G","")
				.replace("GB", "")
				.replace("g", "")
				.replace("M", "")
				.replace("MB", "")
				.replace("m", "")
				.replace("K", "")
				.replace("KB", "")
				.replace("k", "")
				.replace("%","");
		return Double.parseDouble(val);
	}
}
