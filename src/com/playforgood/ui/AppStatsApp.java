package com.playforgood.ui;

import java.util.ArrayList;
import java.util.Timer;

import com.playforgood.db.AppStatsDataSource;

import android.app.Application;

public class AppStatsApp extends Application
{
  private AppStatsService backgroundService;
  
  private final ArrayList<Timer> timerList = new ArrayList<Timer>();
  
  public void setAppService(AppStatsService service) {
	  backgroundService = service;
  }
  
  public AppStatsService getAppStatsService()
  {
	  return backgroundService;
  }
  

  public ArrayList<Timer> getTimerList()
  {
    return timerList;
  }
  
  public AppStatsDataSource getDataSource()
  {
		if (backgroundService == null) {
			return null;
		}
		return backgroundService.getDataSource();
	}
}