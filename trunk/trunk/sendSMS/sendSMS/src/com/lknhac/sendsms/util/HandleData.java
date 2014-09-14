package com.lknhac.sendsms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.lknhac.sendsms.SendSMSActivity;
import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;

public class HandleData {
	public static void insertData(Context context, String fileName,
			String mClass, String mClassDetails) throws IOException {
		FileReader file = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader(file);
		String line = "";

		PhoneListDao phoneListDao = new PhoneListDao(context);
		while ((line = buffer.readLine()) != null) {
			String[] str = line.split(",");
			ContactItem contactItem = new ContactItem();
			contactItem.setStt(str[0]);
			if (str.length > 2) {
				contactItem.setPhoneNumber(str[2]);
			} else {
				contactItem.setPhoneNumber("");
			}
			contactItem.setmClass(mClass);
			contactItem.setmClassDetails(mClassDetails);
			phoneListDao.insertRow(contactItem);
		}
		buffer.close();
		phoneListDao.close();
	}

	public static void insertSeparateData(Context context, String fileName,
			String mClass, String mClassDetails) throws IOException {
		FileReader file = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader(file);
		String line = "";

		PhoneListDao phoneListDao = new PhoneListDao(context);
		while ((line = buffer.readLine()) != null) {
			String[] str = line.split(",");
			ContactItem contactItem = new ContactItem();
			contactItem.setStt(str[0]);
			contactItem.setContactName(str[1]);
			if (str.length > 2) {
				contactItem.setPhoneNumber(str[2]);
			} else {
				contactItem.setPhoneNumber("");
			}
			contactItem.setmToan(str[3]);
			contactItem.setmTiengViet(str[4]);
			contactItem.setmClass(mClass);
			contactItem.setmClassDetails(mClassDetails);
			phoneListDao.insertRow(contactItem);
		}
		buffer.close();
		phoneListDao.close();
	}

	public static String getFilePath(Context context, String mClass,
			String mClassDetails) {
		File f = new File(context.getCacheDir() + "/" + mClassDetails + ".tsv");
		if (!f.exists())
			try {

				InputStream is = context.getAssets().open(
						"savefile" + "/" + "Khoi" + mClass + "/"
								+ mClassDetails + ".csv");
				int size = is.available();
				byte[] buffer = new byte[size];
				is.read(buffer);
				is.close();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(buffer);
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		return f.getAbsolutePath();
	}

	public static void sendSMS(Context context, String number, String strContent) {
		Log.i("Send SMS", "");

		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("smsto:"));
		smsIntent.setType("vnd.android-dir/mms-sms");

		smsIntent.putExtra("address", number);
		smsIntent.putExtra("sms_body", strContent);
		try {
			context.startActivity(smsIntent);
			Log.i("Finished sending SMS...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(context, "SMS faild, please try again later.",
					Toast.LENGTH_SHORT).show();
		}
	}

	public static String getNumbers(Context context, String mClassDetails,
			int from, int to) {
		String numbers = "";
		List<ContactItem> listSummary = new ArrayList<ContactItem>();
		PhoneListDao summaryDao = new PhoneListDao(context);
		listSummary = summaryDao.selectForClassDetails(mClassDetails);

		// for (ContactItem item : listSummary) {
		//
		// numbers += item.getPhoneNumber() + ";";
		// }
		int mTo = to;
		if (listSummary.size() < to) {
			mTo = listSummary.size();
		}
		for (int i = from; i < mTo; i++) {
			numbers += listSummary.get(i).getPhoneNumber() + ";";
		}

		summaryDao.close();
		return numbers;
	}
}
