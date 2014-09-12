package com.lknhac.sendsms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lknhac.sendsms.adapter.ExpandableListAdapter;
import com.lknhac.sendsms.util.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ListClassActitvity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_class);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Expanded",
				// Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Collapsed",
				// Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				String mClass= String.valueOf(groupPosition + 2);
				String mClassDetails = String.valueOf(groupPosition + 2) + "."
						+ String.valueOf(childPosition+1);
				
				Bundle bundle = new Bundle();
				bundle.putString(Const.CLASS,
						mClass);
				bundle.putString(Const.CLASS_DETAILS,
						mClassDetails);
				// After all data has been entered and calculated, go to new
				// page for results
				Intent myIntent = new Intent();
				myIntent.putExtras(bundle);
				myIntent.setClass(getBaseContext(), ListDataActivity.class);
				startActivity(myIntent);
				// Add the bundle into myIntent for referencing variables
				

//				Toast.makeText(
//						getApplicationContext(),
//						mClass
//								+ " : "
//								+ mClassDetails, Toast.LENGTH_SHORT)
//						.show();
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add(getString(R.string.khoi_2));
		listDataHeader.add(getString(R.string.khoi_3));
		listDataHeader.add(getString(R.string.khoi_4));
		listDataHeader.add(getString(R.string.khoi_5));

		// Adding child data
		List<String> Khoi2 = new ArrayList<String>();
		Khoi2.add(getString(R.string.lop_21));
		Khoi2.add(getString(R.string.lop_22));
		Khoi2.add(getString(R.string.lop_23));
		Khoi2.add(getString(R.string.lop_24));
		Khoi2.add(getString(R.string.lop_25));
		Khoi2.add(getString(R.string.lop_26));
		Khoi2.add(getString(R.string.lop_27));
		Khoi2.add(getString(R.string.lop_28));

		List<String> Khoi3 = new ArrayList<String>();
		Khoi3.add(getString(R.string.lop_31));
		Khoi3.add(getString(R.string.lop_32));
		Khoi3.add(getString(R.string.lop_33));
		Khoi3.add(getString(R.string.lop_34));
		Khoi3.add(getString(R.string.lop_35));
		Khoi3.add(getString(R.string.lop_36));
		Khoi3.add(getString(R.string.lop_37));

		List<String> Khoi4 = new ArrayList<String>();
		Khoi4.add(getString(R.string.lop_41));
		Khoi4.add(getString(R.string.lop_42));
		Khoi4.add(getString(R.string.lop_43));
		Khoi4.add(getString(R.string.lop_44));
		Khoi4.add(getString(R.string.lop_45));
		Khoi4.add(getString(R.string.lop_46));
		Khoi4.add(getString(R.string.lop_47));

		List<String> Khoi5 = new ArrayList<String>();
		Khoi5.add(getString(R.string.lop_51));
		Khoi5.add(getString(R.string.lop_52));
		Khoi5.add(getString(R.string.lop_53));
		Khoi5.add(getString(R.string.lop_54));
		Khoi5.add(getString(R.string.lop_55));
		Khoi5.add(getString(R.string.lop_56));
		Khoi5.add(getString(R.string.lop_57));

		listDataChild.put(listDataHeader.get(0), Khoi2); // Header, Child data
		listDataChild.put(listDataHeader.get(1), Khoi3);
		listDataChild.put(listDataHeader.get(2), Khoi4);
		listDataChild.put(listDataHeader.get(3), Khoi5);
	}
}
