package com.playforgood.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.good2play.R;
import com.playforgood.db.AppStatsDataSource;
import com.playforgood.stats.AppStats;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class NewAppsFragment extends Fragment {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 CustomGridViewAdapter customGridAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_monitored_apps, container, false);
		
		//set grid view item
		int flags = PackageManager.GET_META_DATA | 
	            PackageManager.GET_SHARED_LIBRARY_FILES |     
	            PackageManager.GET_UNINSTALLED_PACKAGES;

	PackageManager pm = getActivity().getPackageManager();
    AppStatsDataSource datasource = ((AppStatsApp)getActivity().getApplication()).getDataSource();
    ArrayList<String> existingApps = new ArrayList<String>();
    
    
    if (datasource != null) {
	    List<AppStats> stats = datasource.getAllAppStatss();
	    
	    for (AppStats a : stats) {
	    	existingApps.add(a.getappName());
	    }
	    }
	List<ApplicationInfo> applications = pm.getInstalledApplications(flags);
	for (ApplicationInfo appInfo : applications) {
	    if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
	        // System application
	    } 
	    else if (!existingApps.contains(appInfo.packageName)) {
	    	
	        // Installed by user
	    	Drawable icon = pm.getApplicationIcon(appInfo);
	    	
			Bitmap hcon = ((BitmapDrawable)icon).getBitmap();
			gridArray.add(new Item(hcon, pm.getApplicationLabel(appInfo).toString(), appInfo.packageName));
	    }
	}
		
		gridView = (GridView) view.findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(inflater.getContext(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Item item = gridArray.get(position);
				Fragment df = new DefaultSettingsFragment(item);
				
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, df).commit();
			}

		});
		return view;		
	}
}
