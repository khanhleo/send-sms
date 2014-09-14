package com.lknhac.sendsms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;

public class SendSMSActivity extends Activity {

	private static final String TAG = "SendSMSActivity";
	private EditText txt_content, txt_from, txt_to;
	private List<ContactItem> listSummary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_sms);
		init();
	}

	public void clickSend(View view) {
		sendData();
	}

	private void sendData() {
		String number = "";
		PhoneListDao summaryDao = new PhoneListDao(SendSMSActivity.this);
		listSummary = summaryDao.selectForClassDetails("6.1");
		try {
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> parts = smsManager.divideMessage("");
			smsManager.sendMultipartTextMessage("01228760657", null, parts, null, null);
//			smsManager.sendTextMessage("01228760657", null, , null,
//					null);
			Log.v(TAG, "SMS Sent!");
		} catch (Exception e) {
			e.printStackTrace();
			Log.v(TAG, "SMS faild");
		}
		Log.d(TAG, listSummary.get(0).getPhoneNumber() + ": "
				+ txt_content.getText().toString());
		for (ContactItem item : listSummary) {
			// ContentValues values = new ContentValues();
			// values.put("address", item.getPhoneNumber());// sender name
			// values.put("body", txt_content.toString());
			// getContentResolver().insert(Uri.parse("content://sms/inbox"),
			// values);

			// SmsManager smsManager = SmsManager.getDefault();
			// smsManager
			// .sendTextMessage(
			// item.getPhoneNumber(),
			// null,
			// txt_content.getText().toString(),
			// null, null);
			// Log.d(TAG,item.getPhoneNumber());
			number += item.getPhoneNumber() + ";";
		}

		summaryDao.close();
		// sendSMS(number);
	}

	protected void sendSMS(String number) {
		Log.i("Send SMS", "");

		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("smsto:"));
		smsIntent.setType("vnd.android-dir/mms-sms");

		smsIntent.putExtra("address", number);
		smsIntent.putExtra("sms_body", txt_content.getText().toString());
		try {
			startActivity(smsIntent);
			finish();
			Log.i("Finished sending SMS...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(SendSMSActivity.this,
					"SMS faild, please try again later.", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void init() {
		txt_content = (EditText) findViewById(R.id.txt_content);
		txt_from = (EditText) findViewById(R.id.txt_from);
		txt_to = (EditText) findViewById(R.id.txt_to);
	}

}
