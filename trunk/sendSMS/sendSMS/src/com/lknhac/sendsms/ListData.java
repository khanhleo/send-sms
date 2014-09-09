package com.lknhac.sendsms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;


public class ListData extends Activity {
	private List<ContactItem> listSummary;
	private ContactItem sumItem;
	// private List<String> lName;
	// private List<String> lTime;
	// private List<Integer> lIcon;
	// private List<String> lColor;
	private SummaryAdapter adapter;

//	public static SummaryItem summaryItem;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.summary);
	        // test revisions
	        PhoneListDao summaryDao = new PhoneListDao(ListData.this);
			listSummary = summaryDao.selectAll();
			//init();
			// getList(listSummary);
			final ListView listView = (ListView) findViewById(R.id.lvSummary);
			adapter = new SummaryAdapter(this);
			listView.setAdapter(adapter);
			adapter.setListContact(listSummary);
			adapter.notifyDataSetChanged();
		//	Utils.setListViewHeightBasedOnChildren(listView);
			listView.setDivider(null);
			listView.setDividerHeight(0);
			Toast.makeText(getApplicationContext(), listSummary.get(0).getPhoneNumber(), Toast.LENGTH_SHORT).show();
	 }
	 
	private class SummaryAdapter extends BaseAdapter {
		private Context mContext;
		private List<ContactItem> listSummary = new ArrayList<ContactItem>();

		public SummaryAdapter(Context c) {
			mContext = c;
		}

		@Override
		public ContactItem getItem(int position) {
			return listSummary.get(position);
		}

		@Override
		public int getCount() {
			return listSummary.size();
		}

		public void setListContact(List<ContactItem> summary) {
			List<ContactItem> result = new ArrayList<ContactItem>();
			for (ContactItem item : summary) {
				result.add(item);
			}
			listSummary = result;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ContactItem contactItem = getItem(position);
			View v = convertView;
			if (v == null) {
				v = LayoutInflater.from(mContext).inflate(
						R.layout.listview_summary_item, null);
			}

			TextView itemName = (TextView) v.findViewById(R.id.itemSummaryName);
			TextView itemStt = (TextView) v.findViewById(R.id.itemSummaryStt);

			itemName.setText(contactItem.getPhoneNumber());
			final View itemColor = v.findViewById(R.id.itemSummaryColor);
			if (position % 2 != 0)
				itemColor.setBackgroundColor(Color
						.parseColor(getString(R.color.white)));
			else
				itemColor.setBackgroundColor(Color
						.parseColor(getString(R.color.light_green)));
				itemStt.setText(contactItem.getStt());	

			return v;
		}

		@Override
		public long getItemId(int paramInt) {
			return 0;
		}
	}
}
