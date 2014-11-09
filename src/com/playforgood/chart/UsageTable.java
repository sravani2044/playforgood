package com.playforgood.chart;

import java.util.List;

import com.playforgood.stats.AppStats;
import com.playforgood.ui.AppStatsApp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
 
public class UsageTable{
 
     String apps[];
     String money[];
 
     TableLayout tl;
     TableRow tr;
     TextView appview,moneyview;
 
 Context context;

	public UsageTable(TableLayout lt, Context cn) {
		// TODO Auto-generated constructor stub
		tl =lt;
		//tl.setBackgroundColor(Color.WHITE);
		context = cn;
		addHeaders();
		addData();
	}

	/** This function add the headers to the table **/
    @SuppressWarnings("deprecation")
	public void addHeaders(){
 
         /** Create a TableRow dynamically **/
        tr = new TableRow(context);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        /** Creating a TextView to add to the row **/
        TextView appName = new TextView(context);
        appName.setText("Application");
        appName.setTextColor(Color.GRAY);
        appName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        appName.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        appName.setPadding(5, 5, 5, 0);
        tr.addView(appName);  // Adding textView to tablerow.
 
        /** Creating another textview **/
        TextView donation = new TextView(context);
        donation.setText("Time");
        donation.setTextColor(Color.GRAY);
        donation.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        donation.setPadding(5, 5, 5, 0);
        donation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(donation); // Adding textView to tablerow.
 
        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(context);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        /** Creating another textview **/
        TextView divider = new TextView(context);
        divider.setText("-----------------");
        divider.setTextColor(Color.GREEN);
        divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.
 
        TextView divider2 = new TextView(context);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.
 
        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
    }
 
    /** This function add the data to the table **/
    @SuppressWarnings("deprecation")
	public void addData()
    {

        List<AppStats> appStats = ((AppStatsApp) context).getAppStatsService().getDataSource().getAllAppStatss();
        
        for (AppStats a : appStats) {        	
        	if (a.getappProp().equals("DEFAULT_TITLE")) {
        		continue;
        	}
        
            /** Create a TableRow dynamically **/
            tr = new TableRow(context);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
 
            /** Creating a TextView to add to the row **/
            appview = new TextView(context);
            appview.setText(a.getappProp());
            appview.setTextColor(Color.GRAY);
            appview.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            appview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            appview.setPadding(5, 5, 5, 5);
            tr.addView(appview);  // Adding textView to tablerow.
 
            /** Creating another textview **/
            moneyview = new TextView(context);
            moneyview.setText(String.valueOf(a.getappUsageTime()));
            moneyview.setTextColor(Color.GRAY);
            moneyview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            moneyview.setPadding(5, 5, 5, 5);
            moneyview.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(moneyview); // Adding textView to tablerow.
 
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            
        }
    }
}