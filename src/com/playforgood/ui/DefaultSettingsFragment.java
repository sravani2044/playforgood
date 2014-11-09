package com.playforgood.ui;

import com.example.good2play.R;
import com.playforgood.db.AppStatsDataSource;
import com.playforgood.stats.AppStats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DefaultSettingsFragment extends Fragment {
	final Item item;

	public DefaultSettingsFragment(Item item) {
		super();
		this.item = item;
	}

	public DefaultSettingsFragment() {
		super();
		this.item = Item.DEFAULT_ITEM;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_s_default,
				container, false);
		Button button = (Button) view.findViewById(R.id.savebutton);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Spinner ngoSpinner = (Spinner) view
						.findViewById(R.id.ngospinner);
				TextView hours = (TextView) view.findViewById(R.id.hourstext);
				TextView moneyText = (TextView) view
						.findViewById(R.id.donationtext);

				int hr;
				long money;
				if (hours == null || hours.getText().toString().equals("")) {
					hr = 1;
				} else {
					hr = Integer.valueOf(hours.getText().toString());
				}

				if (moneyText == null
						|| moneyText.getText().toString().equals("")) {
					money = 0;
				} else {
					money = Long.valueOf(moneyText.getText().toString());
				}

				AppStatsDataSource datasource = ((AppStatsApp) getActivity()
						.getApplication()).getDataSource();
				AppStats a = new AppStats();
				a.setappName(item.packageName);
				a.setappProp(item.getTitle());
				a.setappUsageTime(0);
				a.setAppGenMoney(0);
				a.setAppWeeklyTime(0);
				a.setAppMonthlyTime(0);
				a.setAppMinTime(hr);
				a.setAppMinMoney(money);
				a.setAppNGO(ngoSpinner.getSelectedItem().toString());
				datasource.createAppStats(a);

				Fragment mf = new MonitoredAppsFragment();

				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, mf).commit();
			}
		});
		Button cancel_button = (Button) view.findViewById(R.id.cancelbutton);

		cancel_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment mf = new MonitoredAppsFragment();

				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, mf).commit();
			}
		});
		return view;
	}
}
