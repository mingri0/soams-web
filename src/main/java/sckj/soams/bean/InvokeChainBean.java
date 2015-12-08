package sckj.soams.bean;

import sckj.soams.entity.InvokeChain;

public class InvokeChainBean extends InvokeChain {
	
	private String lev;
	
	private String ind;
	
	private String nextlev;
	
	private String nextind;
	
	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getInd() {
		return ind;
	}

	public void setInd(String ind) {
		this.ind = ind;
	}
	public String getNextlev() {
		return nextlev;
	}

	public void setNextlev(String nextlev) {
		this.nextlev = nextlev;
	}

	public String getNextind() {
		return nextind;
	}

	public void setNextind(String nextind) {
		this.nextind = nextind;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getInvoke_seq()==((InvokeChainBean)obj).getInvoke_seq();
	}
}
