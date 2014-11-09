package com.playforgood.ui;

import java.util.ArrayList;
import java.util.Timer;

import com.example.good2play.R;
import com.playforgood.ui.AppStatsService.LocalBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private AppStatsService backgroundService;

    private static Timer timer;

    private ArrayList<Timer> timerList;
    
	  private ServiceConnection serviceConnection = new ServiceConnection()
	  {


		@Override
	    public void onServiceConnected(ComponentName className, IBinder service)
	    {
	    	timerList = ((AppStatsApp)getApplicationContext()).getTimerList();
	    	
	    	for (Timer timr : timerList) {
	    	timr.cancel();
	    	timr.purge();
	    	}
		      timer = new Timer();
		      timerList.add(timer);
		  	  
	      LocalBinder binder = (LocalBinder)service;
	      backgroundService = binder.getService();
	      backgroundService.start(timer);

	      ((AppStatsApp) getApplicationContext()).setAppService(backgroundService);
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName className)
	    {
	      backgroundService = null;
	    }
	  };

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    getApplicationContext().bindService(
	    		
	      new Intent(this, AppStatsService.class),
	      serviceConnection,
	      Context.BIND_AUTO_CREATE);
	    
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container, ReportCardTabbedFragment.newInstance())
				.commit();
		switch (position) {
		case 0:
			mTitle = getString(R.string.title_home);
			fragmentManager.beginTransaction()
					.replace(R.id.container, new HomeTabbedFragment()).commit();
			break;
		case 1:
			mTitle = getString(R.string.title_reportcard);
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							ReportCardTabbedFragment.newInstance()).commit();
			break;
		case 2:
			mTitle = getString(R.string.app_settings);
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							SettingsTabbedFragment.newInstance()).commit();
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

}
