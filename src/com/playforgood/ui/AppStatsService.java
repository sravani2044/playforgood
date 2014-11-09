package com.playforgood.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.playforgood.db.AppStatsDataSource;
import com.playforgood.stats.AppStats;

import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

public class AppStatsService extends Service {
	private boolean initialized = false;
	private final IBinder mBinder = new LocalBinder();
	private Date lastUpdatedTime = new Date();

	public static int SERVICE_PERIOD = 10000;

	private AppStatsDataSource datasource;
	private Map<String, AppStats> allStats = new HashMap<String, AppStats>();

	public AppStatsService() {
		super();
	}

	public interface ServiceCallback {
		void sendResults(int resultCode, Bundle b);
	}

	public class LocalBinder extends Binder {
		public AppStatsService getService() {
			// Return this instance of the service so clients can call public
			// methods
			return AppStatsService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initialized = true;
		datasource = new AppStatsDataSource(this);
		datasource.open();
		fillProcessList();
	}

	@Override
	public IBinder onBind(Intent intent) {
		if (initialized) {
			return mBinder;
		}
		return null;
	}

	private boolean isDateChanged(Date cd, Date ad) {
		Calendar curCal = new GregorianCalendar();
		curCal.setTime(cd);

		Calendar appCal = new GregorianCalendar();
		appCal.setTime(ad);

		if (curCal.get(Calendar.YEAR) != appCal.get(Calendar.YEAR)) {
			return true;
		}
		if (curCal.get(Calendar.MONTH) != appCal.get(Calendar.MONTH)) {
			return true;
		}
		if (curCal.get(Calendar.DAY_OF_MONTH) != appCal
				.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}

	private boolean addToStatistics(String target)
	{
		boolean statsChanged = false;
		Date now = new Date();
		if (!TextUtils.isEmpty(target)) {
			if (lastUpdatedTime != null && allStats.containsKey(target)) {

				AppStats stats = allStats.get(target);
				long delta = (now.getTime() - lastUpdatedTime.getTime()) / 1000;
				Long time = stats.getappUsageTime();
				Date lastUsedDate = stats.getAppLastUsedDate();
				
				if (isDateChanged(lastUsedDate, now)) {
					stats.setAppLastUsedDate(now);
					// Reset stats
					time = delta;
				}
				else {
					if (time != null) {
						time += delta;
					} 
					else {
						time = Long.valueOf(delta);
					}
				}

				stats.setappUsageTime(time);
				long minMoney = stats.getAppMinMoney();
				long minTime = stats.getappMinTime();
				if (time > minTime && minTime > 0) {
					long money = time * minMoney / minTime;
					stats.setAppGenMoney(money);
				}

				datasource.updateAppStats(stats);
			}
		}
		Log.w("srav", "allStats " + allStats);
		
		lastUpdatedTime = now;

		return statsChanged;

	}

	public void start(Timer timer) {
		Log.w("srav", "in start");
		if (timer != null) {
			timer.schedule(new MonitoringTimerTask(), 500, SERVICE_PERIOD);
		}
	}

	public void stop() {
	}

	private class MonitoringTimerTask extends TimerTask {

		@Override
		public void run() {
			fillProcessList();

			ActivityManager activityManager = (ActivityManager) AppStatsService.this
					.getSystemService(ACTIVITY_SERVICE);
			List<RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
			String current = taskInfo.get(0).topActivity.getPackageName();
			addToStatistics(current);
		}
	}

	private void fillProcessList() {
		List<AppStats> stats = datasource.getAllAppStatss();
		allStats.clear();

		for (AppStats a : stats) {
			allStats.put(a.getappName(), a);
		}
	}

	public AppStatsDataSource getDataSource() {
		return datasource;
	}

}
