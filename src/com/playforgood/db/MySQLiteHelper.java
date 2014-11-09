package com.playforgood.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_APPSTATS = "appstats";

  public static final String COLUMN_APP_NAME = "APP";
  public static final String COLUMN_APP_PROP = "PROP";
  public static final String COLUMN_APP_MIN_TIME = "MINTIME";
  public static final String COLUMN_APP_MIN_MONEY = "MINMONEY";
  public static final String COLUMN_APP_USAGE_TIME = "USAGETIME";
  public static final String COLUMN_APP_GEN_MONEY = "GENMONEY";
  public static final String COLUMN_APP_WEEKLY_TIME = "WEEKTIME";
  public static final String COLUMN_APP_MONTHLY_TIME = "MONTHTIME";
  public static final String COLUMN_APP_NGO = "NGO";
  
  public static final String TABLE_NGOSTATS = "ngostats";

  public static final String COLUMN_NGO_NAME = "NGONAME";
  public static final String COLUMN_NGO_APPS = "NGOAPPS";
  public static final String COLUMN_NGO_DETAILS = "DETAILS";
  public static final String COLUMN_NGO_MONEY = "MONEY";
  public static final String COLUMN_APP_LAST_USED_DATE = "LASTDATE";
  
  private static final String DATABASE_NAME = "appstats.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_APPSTATS + "(" 
	  + COLUMN_APP_NAME + " text not null primary key, " 
      + COLUMN_APP_PROP + " text, " 
      + COLUMN_APP_MIN_TIME + " int, " 
      + COLUMN_APP_MIN_MONEY + " int, " 
      + COLUMN_APP_USAGE_TIME + " int, " 
      + COLUMN_APP_GEN_MONEY + " int, " 
      + COLUMN_APP_WEEKLY_TIME + " int, " 
      + COLUMN_APP_MONTHLY_TIME + " int, " 
      + COLUMN_APP_LAST_USED_DATE + " int, " 
      + COLUMN_APP_NGO + " text);";

  private static final String NGO_DATABASE_CREATE = "create table "
	      + TABLE_NGOSTATS + "(" 
		  + COLUMN_NGO_NAME + " text not null primary key, " 
	      + COLUMN_NGO_DETAILS + " text, " 
	      + COLUMN_NGO_MONEY + " int, " 
	      + COLUMN_NGO_APPS + " text);";


  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
    database.execSQL(NGO_DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPSTATS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGOSTATS);
    onCreate(db);
  }

} 