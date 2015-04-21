package com.lknhac.sendsms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;
import com.lknhac.sendsms.util.Const;
import com.lknhac.sendsms.util.HandleData;

public class ListDataActivity extends Activity {
	private List<ContactItem> listSummary; 
	private SummaryAdapter adapter;
	private String mClass;
	private String mClassDetails;
	private ListView listView;
	private int mFrom = 0, mTo = 0;
	private EditText txtFrom, txtTo;
	private TextView txtLop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.summary);
		PhoneListDao summaryDao = new PhoneListDao(ListDataActivity.this);
		listView = (ListView) findViewById(R.id.lvSummary);
		txtFrom = (EditText) findViewById(R.id.txt_from);
		txtTo = (EditText) findViewById(R.id.txt_to);
		txtLop = (TextView) findViewById(R.id.txt_lop);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mClass = extras.getString(Const.CLASS);
			mClassDetails = extras.getString(Const.CLASS_DETAILS);
//			Toast.makeText(getApplicationContext(),
//					mClass + ":-:" + mClassDetails, Toast.LENGTH_SHORT).show();
			if (mClass != null) {
				if (mClassDetails != null) {
					txtLop.setText("Lop: "+mClassDetails);
					listSummary = summaryDao
							.selectForClassDetails(mClassDetails);
					summaryDao.close();
					if (listSummary != null && listSummary.size() > 0) {
						init();
					}
				}
			}
		}

	}

	// onClick
	public void clickInsertData(View view) {
		if (listSummary != null && listSummary.size() > 0) {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.can_not_insert),
					Toast.LENGTH_SHORT).show();
		} else {

			String fileName = HandleData.getFilePath(ListDataActivity.this,
					mClass, mClassDetails);
			try {
				HandleData.insertData(ListDataActivity.this, fileName, mClass,
						mClassDetails);
				Toast.makeText(getApplicationContext(), "done",
						Toast.LENGTH_SHORT).show();
				PhoneListDao summaryDao = new PhoneListDao(
						ListDataActivity.this);
				listSummary = summaryDao.selectForClassDetails(mClassDetails);
				summaryDao.close();
				init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "fail",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void clickCheckData(View view) {
		if (listSummary != null && listSummary.size() > 0) {
			init();
		} else {
			PhoneListDao summaryDao = new PhoneListDao(ListDataActivity.this);
			listSummary = summaryDao.selectForClassDetails(mClassDetails);
			summaryDao.close();
		}
		if (listSummary != null && listSummary.size() > 0) {

			init();
		} else {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.no_data),
					Toast.LENGTH_SHORT).show();
		}

	}

	public void clickSend(View view) {
		if (listSummary != null && listSummary.size() > 0) {

			if (txtFrom.getText().toString() != null
					&& !txtFrom.getText().toString().equals("")
					&& txtTo.getText().toString() != null
					&& !txtTo.getText().toString().equals("")) {

				mFrom = Integer.parseInt(txtFrom.getText().toString()) - 1;
				mTo = Integer.parseInt(txtTo.getText().toString());

				String numbers = HandleData.getNumbers(ListDataActivity.this,
						mClassDetails, mFrom, mTo);
				Log.d("ListDataActivity", numbers);
				String strContent = "";
				if (mClass.equals("1"))
					strContent = getResources().getString(R.string.content_1);
				else if(mClass.equals("2"))
					strContent = getResources().getString(R.string.content_2);
				else if(mClass.equals("3"))
					strContent = getResources().getString(R.string.content_3);
				else if(mClass.equals("4"))
					strContent = getResources().getString(R.string.content_4);
				else if(mClass.equals("5"))
					strContent = getResources().getString(R.string.content_5);
				HandleData.sendSMS(ListDataActivity.this, numbers, strContent);
			}else{
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.please_input_number),
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.no_data_to_send),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void init() {
		adapter = new SummaryAdapter(this);
		listView.setAdapter(adapter);
		adapter.setListContact(listSummary);
		adapter.notifyDataSetChanged();
		listView.setDivider(null);
		listView.setDividerHeight(0);
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
