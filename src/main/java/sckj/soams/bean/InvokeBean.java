package sckj.soams.bean;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 页面展示实体类
 * @author jackChen
 *
 */
public class InvokeBean {
	
	private String id;
	
	private String application;
	
	private String ip;
	
	private String type;
	
	private String method;
	
	private String status;
	
	private String state;
	
	private String img;
	
	private String timeelapsed;
	
	private List<InvokeBean> children;
	
	public String getState() {
		if(StringUtils.isBlank(state)){
			state=this.children.size()>0?"expland":"";
		}
		return state;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<InvokeBean> getChildren() {
		return children;
	}


	public void setChildren(List<InvokeBean> children) {
		this.children = children;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeelapsed() {
		return timeelapsed;
	}

	public void setTimeelapsed(String timeelapsed) {
		this.timeelapsed = timeelapsed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
