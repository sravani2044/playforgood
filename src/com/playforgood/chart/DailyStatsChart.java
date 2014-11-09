package com.playforgood.chart;

import java.util.List;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.playforgood.stats.AppStats;
import com.playforgood.ui.AppStatsApp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;


public class DailyStatsChart

{
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Test chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "desc of the chart";
  }

  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[] { 20, 30, 15, 0 });
    for (int color : colors) {
      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
      r.setColor(color);
      renderer.addSeriesRenderer(r);
    }
    return renderer;
  }
  /**
   * Builds a multiple category series using the provided values.
   * 
   * @param titles the series titles
   * @param values the values
   * @return the category series
   */
  protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
      String[] titles, double[] values) {
    MultipleCategorySeries series = new MultipleCategorySeries(title);
      series.add("", titles, values);
    return series;
  }
  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public GraphicalView execute(Context context) 
  {
	    Log.w("neha" , "a" + ((AppStatsApp) context).getAppStatsService());
    List<AppStats> appStats = ((AppStatsApp) context).getAppStatsService().getDataSource().getAllAppStatss();
    
    int count = 0;
    for (AppStats a : appStats) {
        if (a.getappUsageTime() != 0 &&
        	!a.getappProp().equals("DEFAULT_TITLE")) {
        	count++;
        }
    }
    
    String[] titles = new String[count];
    double[] values = new double[count];
    int[] colors    = new int[count];
    
    Random rnd = new Random();
    
    int k = 0;
    for (AppStats a : appStats) {
    	
    	if (a.getappProp().equals("DEFAULT_TITLE") ||
    		a.getappUsageTime() == 0) {
            continue;
        }
    	
    	titles[k] = a.getappProp();
    	values[k] = a.getappUsageTime();
    	colors[k] = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    	
    	k++;
    }
    
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setLabelsColor(Color.GRAY);
    return ChartFactory.getDoughnutChartView(context,
        buildMultipleCategoryDataset("Dailystats ", titles, values), renderer);
  }

}