package sckj.soams.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import sckj.soams.SaomsApplication;
import sckj.soams.entity.HostCpuLogs;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SaomsApplication.class })
@TransactionConfiguration(defaultRollback = true)
public class HostCpuLogsServiceTest {

	@Autowired
	private HostCpuLogsService service;
	
	@Ignore
	@Test
	public void test() {
		List<HostCpuLogs> hclList = service.getLastCpuLogs("179eaa5d4f5045fb95b969f09cf4d434", 20);
		assertTrue(hclList.size()==20);
	}

}
