package com.playforgood.stats;

import java.util.Date;

public class AppStats extends Object{
	private String appName;
	private String appProp;
	private Date appLastUsedDate;
	private long appMinTime;
	private long appUsageTime;
	private long appGenMoney;
	private long appWeeklyTime;
	private long appMinMoney;
	
	public long getAppMinTime() {
		return appMinTime;
	}
	public Date getAppLastUsedDate() {
		return appLastUsedDate;
	}
	public void setAppLastUsedDate(Date appLastUsedDate) {
		this.appLastUsedDate = appLastUsedDate;
	}
	public void setAppMinTime(long appMinTime) {
		this.appMinTime = appMinTime;
	}
	public long getAppMinMoney() {
		return appMinMoney;
	}
	public void setAppMinMoney(long appMinMoney) {
		this.appMinMoney = appMinMoney;
	}
	public long getAppWeeklyTime() {
		return appWeeklyTime;
	}
	public void setAppWeeklyTime(long appWeeklyTime) {
		this.appWeeklyTime = appWeeklyTime;
	}
	public long getAppMonthlyTime() {
		return appMonthlyTime;
	}
	public void setAppMonthlyTime(long appMonthlyTime) {
		this.appMonthlyTime = appMonthlyTime;
	}
	public String getAppNGO() {
		return appNGO;
	}
	public void setAppNGO(String appNGO) {
		this.appNGO = appNGO;
	}
	private long appMonthlyTime;
	private String appNGO;
	public long getAppGenMoney() {
		return appGenMoney;
	}
	public void setAppGenMoney(long appGenMoney) {
		this.appGenMoney = appGenMoney;
	}
	public String getappName() {
		return appName;
	}
    public boolean equals(AppStats o) {
        return o.getappName().equals(o.getappName());
    }
    
    public int hashCode() {
    	return appName.hashCode();
    }    
	public void setappName(String appName) {
		this.appName = appName;
	}
	public long getappMinTime() {
		return appMinTime;
	}
	public void setappProp(String appProp) {
		this.appProp = appProp;
	}
	public String getappProp() {
		return appProp;
	}
	public long getappUsageTime() {
		return appUsageTime;
	}
	public void setappUsageTime(long appUsageTime) {
		this.appUsageTime = appUsageTime;
	}
}
