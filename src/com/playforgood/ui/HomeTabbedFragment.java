package com.playforgood.ui;

import com.example.good2play.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeTabbedFragment extends Fragment {

	public static final String TAG = HomeTabbedFragment.class
			.getSimpleName();
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	public static HomeTabbedFragment newInstance() {
		return new HomeTabbedFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container,
				false);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager());

		mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		return v;
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment1 = new MonitoredAppsFragment();
			Fragment fragment2 = new NewAppsFragment();
			switch (position) {
			case 0:
				return fragment1;
			case 1:
				return fragment2;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "MONITORED APPS";
			case 1:
				return "NEW APPS";
			}
			return null;
		}
	}
}