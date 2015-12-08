package sckj.soams.reset;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sckj.soams.bean.InvokeBean;
import sckj.soams.bean.PageBean;
import sckj.soams.bean.UiTreeGrid;
import sckj.soams.entity.InvokeChain;
import sckj.soams.service.InvokeChainService;

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
		pb.setEnd(100);
		List<InvokeBean> idList = service.getInvokeMain(pb);
		UiTreeGrid grid = new UiTreeGrid(idList);
		return grid;
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
}
