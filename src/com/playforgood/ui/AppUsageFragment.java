package com.playforgood.ui;

import com.example.good2play.R;
import com.playforgood.chart.UsageTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class AppUsageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_appusage, container, false);
		
		TableLayout lt = (TableLayout) view.findViewById(R.id.appusagetable);
	    new UsageTable(lt, getActivity().getApplicationContext());
		return view;		
	}
}
