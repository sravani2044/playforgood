package com.playforgood.stats;

public class NGOStats extends Object{
	private String ngoName;
	public String getNgoName() {
		return ngoName;
	}
	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}
	public long getNgoMoney() {
		return ngoMoney;
	}
	public void setNgoMoney(long ngoMoney) {
		this.ngoMoney = ngoMoney;
	}
	public String getNgoDetails() {
		return ngoDetails;
	}
	public void setNgoDetails(String ngoDetails) {
		this.ngoDetails = ngoDetails;
	}
	public String getNgoApps() {
		return ngoApps;
	}
	public void setNgoApps(String ngoApps) {
		this.ngoApps = ngoApps;
	}
	private long ngoMoney;
	private String ngoDetails;
	private String ngoApps;

}
