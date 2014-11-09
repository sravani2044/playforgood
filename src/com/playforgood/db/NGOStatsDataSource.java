package com.playforgood.db;

import java.util.ArrayList;
import java.util.List;

import com.playforgood.stats.NGOStats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NGOStatsDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_NGO_NAME,
		  MySQLiteHelper.COLUMN_NGO_DETAILS,
      MySQLiteHelper.COLUMN_NGO_MONEY,
      MySQLiteHelper.COLUMN_NGO_APPS};


  public NGOStatsDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public NGOStats createNgoStats(NGOStats a) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_NGO_NAME, a.getNgoName());
    values.put(MySQLiteHelper.COLUMN_NGO_MONEY, a.getNgoMoney());
     values.put(MySQLiteHelper.COLUMN_NGO_DETAILS, a.getNgoDetails());
    values.put(MySQLiteHelper.COLUMN_NGO_APPS, a.getNgoApps());
    database.insert(MySQLiteHelper.TABLE_NGOSTATS, null, values);
    return a;
  }

  public NGOStats updateNGOStats(NGOStats a) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_NGO_NAME, a.getNgoName());
	    values.put(MySQLiteHelper.COLUMN_NGO_MONEY, a.getNgoMoney());
	     values.put(MySQLiteHelper.COLUMN_NGO_DETAILS, a.getNgoDetails());
	    values.put(MySQLiteHelper.COLUMN_NGO_APPS, a.getNgoApps());
	    database.update(MySQLiteHelper.TABLE_NGOSTATS, 
	            values, MySQLiteHelper.COLUMN_NGO_NAME
	            + " = \'" + a.getNgoName() +"\'", null);
	    return a;
	  }

  public void deleteNGOStats(String id) {
    
    System.out.println("NGOStats deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_NGOSTATS, MySQLiteHelper.COLUMN_NGO_NAME
        + " = \'" + id +"\'", null);
  }

  public List<NGOStats> getAllNGOStatss() {
    List<NGOStats> NGOStatss = new ArrayList<NGOStats>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_NGOSTATS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      NGOStats NGOStats = cursorToNGOStats(cursor);
      NGOStatss.add(NGOStats);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return NGOStatss;
  }

  private NGOStats cursorToNGOStats(Cursor cursor) {
    NGOStats NGOStats = new NGOStats();
    NGOStats.setNgoName(cursor.getString(0));
    NGOStats.setNgoDetails(cursor.getString(1));
    NGOStats.setNgoMoney(cursor.getInt(2));
    NGOStats.setNgoApps(cursor.getString(3));
    return NGOStats;
  }
} 