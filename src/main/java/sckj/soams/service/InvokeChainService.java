package sckj.soams.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sckj.soams.Constants;
import sckj.soams.bean.InvokeBean;
import sckj.soams.bean.InvokeChainBean;
import sckj.soams.bean.PageBean;
import sckj.soams.entity.InvokeChain;
import sckj.soams.mapping.InvokeChainMapper;

@Service
public class InvokeChainService {
	private static final Logger logger = LoggerFactory
			.getLogger(InvokeChainService.class);

	@Autowired
	private InvokeChainMapper mapper;

	/**
	 * 获取调用链记录
	 * 
	 * @param pb
	 * @return
	 */
	public List<InvokeChain> getInvokeChainRecordsByPage(PageBean pb) {
		return mapper.selectInvokeChainRecordsByPage(pb);
	}

	public List<InvokeBean> getInvokeMain(PageBean pb) {
		List<InvokeBean> ibList = new ArrayList<InvokeBean>();
		List<InvokeChain> icList = getInvokeSlow(pb);
		for (InvokeChain ic : icList) {
			InvokeBean ib = new InvokeBean();
			ib.setApplication(ic.getApplication());
			ib.setId("i_"+ic.getInvoke_seq().toString());
			ib.setIp(ic.getIp());
			ib.setMethod(ic.getRequesturl());
			ib.setStatus(ic.getStatus());
			ib.setState("closed");
			ib.setTimeelapsed(ic.getTimeelapsed());
			ib.setType(ic.getType());
			ibList.add(ib);
		}
		return ibList;
	}

	public List<InvokeBean> getInvokeDetail(String invokeseq) {
		Long invoke_seq = Long.parseLong(invokeseq);
		InvokeChain fromicb = mapper.selectByPrimaryKey(invoke_seq);
		List<InvokeChainBean> icList = mapper
				.selectInvokeChainBySeq(invoke_seq);// 先将当前调用链全部取出。然后根据调用的顺序组织树
		List<InvokeBean> ibList = new ArrayList<InvokeBean>();

		Map<Integer, String> inMap = Constants.invokeOrder(fromicb
				.getApplication());
		// EBS或者APP是入口的，即所有的controller
		List<InvokeChainBean> ibList1 = getSameChainList(
				fromicb.getApplication(), inMap.get(1), icList);
		// 获取当前级别的下一级别的节点
		for (InvokeChainBean icb : ibList1) {
			InvokeBean ib = fillInvokeBean(icb);
			ib.setChildren(getChildInvokeChain(icb, icList));
			ibList.add(ib);
		}

		return ibList;
	}

	private InvokeBean fillInvokeBean(InvokeChainBean icb) {
		InvokeBean ib = new InvokeBean();
		ib.setId(icb.getInvoke_seq().toString());
		ib.setApplication(icb.getApplication());
		ib.setIp(icb.getIp());
		ib.setMethod(icb.getMethod());
		ib.setStatus(icb.getStatus());
		ib.setTimeelapsed(icb.getTimeelapsed());
		ib.setType(icb.getType());
		return ib;
	}

	/**
	 * 当前节点的子节点有2类，一类是同系统的，一类是不同系统的
	 * 
	 * @param icb
	 * @param icbList
	 * @return
	 */
	private List<InvokeBean> getChildInvokeChain(InvokeChainBean picb,
			List<InvokeChainBean> icbList) {
		List<InvokeBean> ibList = new ArrayList<InvokeBean>();
		// 1、先找同一系统同一下的子节点 service节点
		List<InvokeChainBean> ibList1 = getSameChainList(picb.getApplication(),
				getNextType(picb.getApplication(), picb.getType()), icbList);
		for (InvokeChainBean icb : ibList1) {// eg service
			if (Integer.parseInt(icb.getInd()) > Integer.parseInt(picb.getInd())
					&& Integer.parseInt(icb.getInd()) < Integer
							.parseInt(StringUtils.isBlank(picb.getNextind()) ? "999999"
									: icb.getNextind())) {
				InvokeBean ib = fillInvokeBean(icb);
				ib.setChildren(getChildInvokeChain(icb, icbList));
				ibList.add(ib);
			}
		}
		//2、查找不同系统的
		List<InvokeChainBean> ibList2 = getNextLevChainList(picb.getRpcid(),picb.getLev(),picb.getInd(),icbList);
		if(ibList2.size()>0){
			InvokeBean otherIb = null;
			for(InvokeChainBean icb:ibList2){
				if("1".equals(icb.getInd())){
					otherIb = fillInvokeBean(icb);
					otherIb.setId(icb.getApplication()+otherIb.getId());
					otherIb.setMethod("调用第其他系统"+icb.getApplication());
					ibList.add(otherIb);
				}
			}
			if(otherIb!=null){
				List<InvokeBean> otheribList = new ArrayList<InvokeBean>();
				for (InvokeChainBean icb:ibList2){
					InvokeBean ib = fillInvokeBean(icb);
					ib.setChildren(getChildInvokeChain(icb, icbList));
					otheribList.add(ib);
				}
				otherIb.setChildren(otheribList);
			}
		}
		return ibList;
	}
	
	
	private List<InvokeChainBean> getNextLevChainList(String pfromrpcid,String plev,String pind,List<InvokeChainBean> picbList){
		List<InvokeChainBean> icbList = new ArrayList<InvokeChainBean>();
		for (InvokeChainBean icb : picbList) {
			String firstType = getFirstType(icb.getApplication());
			if(pfromrpcid.equals(icb.getFromrpcid()) && firstType.equals(icb.getType())){
				icbList.add(icb);
			}
		}
		picbList.removeAll(icbList);
		// 计算每个节点的下个节点ID
		for (int i = 0; i < icbList.size(); i++) {
			if (i != icbList.size() - 1) {// 如果不是最后一个节点。都需要设置下一届点的RPCID
				icbList.get(i).setNextind(icbList.get(i + 1).getInd());
			}
		}
		return icbList;
	}
	
	private String getFirstType(String application){
		Map<Integer, String> map = Constants.invokeOrder(application);
		return map.get(1);
	}

	private String getNextType(String application, String type) {
		Map<Integer, String> map = Constants.invokeOrder(application);
		for (int index : map.keySet()) {
			if (type.equals(map.get(index))) {
				if (map.keySet().contains(index + 1)) {
					return map.get(index + 1);
				}
			}
		}
		return "";
	}

	/**
	 * 获取同类型节点
	 * 
	 * @param application
	 * @param type
	 * @param icList
	 * @return
	 */
	private List<InvokeChainBean> getSameChainList(String application,
			String type, List<InvokeChainBean> icList) {
		List<InvokeChainBean> sicbList = new ArrayList<InvokeChainBean>();
		for (InvokeChainBean icb : icList) {
			if (type.equals(icb.getType()) && application.equals(application)) {
				sicbList.add(icb);
			}
		}
		icList.removeAll(sicbList);
		// 计算每个节点的下个节点ID
		for (int i = 0; i < sicbList.size(); i++) {
			if (i != sicbList.size() - 1) {// 如果不是最后一个节点。都需要设置下一届点的RPCID
				sicbList.get(i).setNextind(sicbList.get(i + 1).getInd());
			}
		}
		return sicbList;
	}

	public String getLastInvokeChain() {
		return mapper.getLastInvokeChainDt();
	}

	public Integer getInvokeCount(String category) {
		return mapper.getInvokeCount(category.substring(0,16));
	}
	
	public Integer getInvokeErrorCount(String category) {
		return mapper.getInvokeErrorCount(category.substring(0,16));
	}

	public List<InvokeChain> getInvokeSlow(PageBean pb) {
		return mapper.selectInvokeSlowRecordsByPage(pb);
	}

}
