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

public class SettingsTabbedFragment extends Fragment {

	public static final String TAG = SettingsTabbedFragment.class
			.getSimpleName();
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	public static SettingsTabbedFragment newInstance() {
		return new SettingsTabbedFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_settings, container,
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
			Fragment fragment3 = new ProfileSettingsFragment();
			Fragment fragment2 = new NotificationSettingsFragment();
			Fragment fragment1 = new DefaultSettingsFragment();
			switch (position) {
			case 0:
				return fragment1;
			case 1:
				return fragment2;
			case 2:
				return fragment3;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "DEFAULT SETTINGS";
			case 1:
				return "NOTIFICATIONS";
			case 2:
				return "PROFILE";
			}
			return null;
		}
	}
}