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
 
public class CreateTable
{
 
     String apps[];
     String money[];
 
     TableLayout tl;
     TableRow tr;
     TextView appview,moneyview,ngoview;
 
 Context context;

	public CreateTable(TableLayout lt, Context cn)
	{
		tl =lt;
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
        TextView ngo = new TextView(context);
        ngo.setText("NGO");
        ngo.setTextColor(Color.GRAY);
        ngo.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        ngo.setPadding(5, 5, 5, 0);
        ngo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ngo); // Adding textView to tablerow.
        
        /** Creating another textview **/
        TextView donation = new TextView(context);
        donation.setText("Donation");
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
        divider2.setText("---------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.
 
        TextView divider3 = new TextView(context);
        divider3.setText("-----------------");
        divider3.setTextColor(Color.GREEN);
        divider3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider3.setPadding(5, 0, 0, 0);
        divider3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider3); // Adding textView to tablerow.
 
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
            ngoview = new TextView(context);
            ngoview.setText(String.valueOf(a.getAppNGO()));
            ngoview.setTextColor(Color.GRAY);
            ngoview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            ngoview.setPadding(5, 5, 5, 5);
            ngoview.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ngoview); // Adding textView to tablerow.
            
            /** Creating another textview **/
            moneyview = new TextView(context);
            moneyview.setText(String.valueOf(a.getAppGenMoney()));
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