package sckj.soams.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSONObject;

import sckj.soams.SaomsApplication;
import sckj.soams.bean.InvokeBean;
import sckj.soams.bean.PageBean;
import sckj.soams.entity.InvokeChain;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SaomsApplication.class })
@TransactionConfiguration(defaultRollback = true)
public class InvokeChainServiceTest {
	
	@Autowired
	private InvokeChainService service;
	@Ignore
	@Test
	public void testGetInvokeChainRecordsByPage() {
		PageBean pb = new PageBean();
		pb.setStart(0);
		pb.setEnd(100);
		List<InvokeChain> li=service.getInvokeChainRecordsByPage(pb);
		System.out.println(li.size());
	}
	
	@Test
	public void testGetInvokeDetail(){
//		List<InvokeBean> ibList = service.getInvokeDetail("1");
//		System.out.println(JSONObject.toJSONString(ibList));
	}
	@Ignore
	@Test
	public void testGetLastInvokeChainDt(){
		String dt = service.getLastInvokeChain();
		System.out.println(dt);
		assertTrue(StringUtils.isNotBlank(dt));
	}
	
	@Test
	public void testGetInvokeCount() {
		int s = service.getInvokeCount("2015-12-08 15:34:20");
		System.out.println("all="+s);
	}
	@Test
	public void getInvokeErrorCount() {
		int s = service.getInvokeErrorCount("2015-12-08 15:34:20");
		System.out.println("error="+s);
	}
}
