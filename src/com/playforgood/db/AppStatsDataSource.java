package com.playforgood.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.playforgood.stats.AppStats;

public class AppStatsDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_APP_NAME,
		  MySQLiteHelper.COLUMN_APP_PROP,
      MySQLiteHelper.COLUMN_APP_MIN_TIME,
      MySQLiteHelper.COLUMN_APP_MIN_MONEY,
      MySQLiteHelper.COLUMN_APP_USAGE_TIME,
      MySQLiteHelper.COLUMN_APP_GEN_MONEY,
      MySQLiteHelper.COLUMN_APP_WEEKLY_TIME,
      MySQLiteHelper.COLUMN_APP_MONTHLY_TIME,
      MySQLiteHelper.COLUMN_APP_LAST_USED_DATE,
      MySQLiteHelper.COLUMN_APP_NGO};


  public AppStatsDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public AppStats createAppStats(AppStats a) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_APP_NAME, a.getappName());
    values.put(MySQLiteHelper.COLUMN_APP_PROP, a.getappProp());
     values.put(MySQLiteHelper.COLUMN_APP_MIN_TIME, a.getappMinTime());
     values.put(MySQLiteHelper.COLUMN_APP_MIN_MONEY, a.getAppMinMoney());
    values.put(MySQLiteHelper.COLUMN_APP_USAGE_TIME, a.getappUsageTime());
    values.put(MySQLiteHelper.COLUMN_APP_GEN_MONEY, a.getAppGenMoney());
    values.put(MySQLiteHelper.COLUMN_APP_WEEKLY_TIME, a.getAppWeeklyTime());
    values.put(MySQLiteHelper.COLUMN_APP_MONTHLY_TIME, a.getAppMonthlyTime());
    values.put(MySQLiteHelper.COLUMN_APP_LAST_USED_DATE, new Date().getTime());
    values.put(MySQLiteHelper.COLUMN_APP_NGO, a.getAppNGO());
    database.insert(MySQLiteHelper.TABLE_APPSTATS, null,
        values);
    return a;
  }

  public AppStats updateAppStats(AppStats a) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_APP_NAME, a.getappName());
	    values.put(MySQLiteHelper.COLUMN_APP_PROP, a.getappProp());
	     values.put(MySQLiteHelper.COLUMN_APP_MIN_TIME, a.getappMinTime());
	     values.put(MySQLiteHelper.COLUMN_APP_MIN_MONEY, a.getAppMinMoney());
	    values.put(MySQLiteHelper.COLUMN_APP_USAGE_TIME, a.getappUsageTime());
	    values.put(MySQLiteHelper.COLUMN_APP_GEN_MONEY, a.getAppGenMoney());
	    values.put(MySQLiteHelper.COLUMN_APP_WEEKLY_TIME, a.getAppWeeklyTime());
	    values.put(MySQLiteHelper.COLUMN_APP_MONTHLY_TIME, a.getAppMonthlyTime());
	    values.put(MySQLiteHelper.COLUMN_APP_LAST_USED_DATE, a.getAppLastUsedDate().getTime());
	    values.put(MySQLiteHelper.COLUMN_APP_NGO, a.getAppNGO());
	    database.update(MySQLiteHelper.TABLE_APPSTATS, 
	            values, MySQLiteHelper.COLUMN_APP_NAME
	            + " = \'" + a.getappName() +"\'", null);
	    return a;
	  }

  public void deleteAppStats(String id) {
    
    System.out.println("AppStats deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_APPSTATS, MySQLiteHelper.COLUMN_APP_NAME
        + " = \'" + id +"\'", null);
  }

  public List<AppStats> getAllAppStatss() {
    List<AppStats> appStatss = new ArrayList<AppStats>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_APPSTATS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      AppStats appStats = cursorToAppStats(cursor);
      appStatss.add(appStats);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return appStatss;
  }

  private AppStats cursorToAppStats(Cursor cursor) {
    AppStats appStats = new AppStats();
    appStats.setappName(cursor.getString(0));
    appStats.setappProp(cursor.getString(1));
    appStats.setAppMinTime(cursor.getInt(2));
    appStats.setAppMinMoney(cursor.getInt(3));
    appStats.setappUsageTime(cursor.getInt(4));
    appStats.setAppGenMoney(cursor.getInt(5));
    appStats.setAppWeeklyTime(cursor.getInt(6));
    appStats.setAppMonthlyTime(cursor.getInt(7));
    appStats.setAppLastUsedDate(new Date(cursor.getInt(8)));
    appStats.setAppNGO(cursor.getString(9));
    return appStats;
  }
} 