package sckj.soams.bean;

import java.util.Date;

public class MemorySwapBean {
	
	private Date recdt;
	
	private String mem;
	
	private String memtot;
	
	private String swap;
	
	private String swaptot;

	public Date getRecdt() {
		return recdt;
	}

	public void setRecdt(Date recdt) {
		this.recdt = recdt;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		this.swap = swap;
	}

	public String getMemtot() {
		return memtot;
	}

	public void setMemtot(String memtot) {
		this.memtot = memtot;
	}

	public String getSwaptot() {
		return swaptot;
	}

	public void setSwaptot(String swaptot) {
		this.swaptot = swaptot;
	}
	
}
