package sckj.soams.bean;

import java.util.List;

import sckj.soams.entity.HostDiskInfo;

public class DiskInfoBean {
	
	private double total;
	
	private double used;
	
	private double free;
	
	private double usage;
	
	List<HostDiskInfo> hdiList;
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public List<HostDiskInfo> getHdiList() {
		return hdiList;
	}

	public void setHdiList(List<HostDiskInfo> hdiList) {
		this.hdiList = hdiList;
	}
	
}
