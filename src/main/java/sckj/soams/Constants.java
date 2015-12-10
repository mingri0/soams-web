package sckj.soams;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final Map<Integer,String> WEB_INVOKE_CHAIN;
	
	public static final Map<Integer,String> SERVICE_INVOKE_CHAIN;
	
	public static final String CHART_INTERVAL_REAL = "1";//实时
	
	public static final String CHART_INTERVAL_DAY = "2";//24小时之内
	
	public static Map<Integer, String> invokeOrder(String application) {
		switch (application) {
		case "EBS":
			return WEB_INVOKE_CHAIN;
		case "ERP-SERVICE-USER":
			return SERVICE_INVOKE_CHAIN;
		case "ERP-SERVICE-STOCK":
			return SERVICE_INVOKE_CHAIN;
		case "ERP-SERVICE-ORDER":
			return SERVICE_INVOKE_CHAIN;
		default:
			return WEB_INVOKE_CHAIN;
		}
	}
	
	static{
		WEB_INVOKE_CHAIN = new HashMap<Integer,String>();
		WEB_INVOKE_CHAIN.put( 1, "CONTROLLER");
		WEB_INVOKE_CHAIN.put( 2, "SERVICE");
		WEB_INVOKE_CHAIN.put( 3, "DAO");
		
		SERVICE_INVOKE_CHAIN = new HashMap<Integer,String>();
		SERVICE_INVOKE_CHAIN.put( 1, "FACADEIMPL");
		SERVICE_INVOKE_CHAIN.put( 2, "BIZ");
		SERVICE_INVOKE_CHAIN.put( 3, "DAO");
	}
}
