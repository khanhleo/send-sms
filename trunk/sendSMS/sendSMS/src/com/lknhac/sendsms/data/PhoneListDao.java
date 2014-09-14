package com.lknhac.sendsms.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lknhac.sendsms.object.ContactItem;

public class PhoneListDao extends DaoBase {

	private final static String tableName = DbConstraint.EXCEPTIONS;
	private String[] mColumns = new String[] { DbConstraint.CLASS,
			DbConstraint.CLASS_DETAILS, DbConstraint.STT, DbConstraint.NAME,
			DbConstraint.PHONES,DbConstraint.TOAN,DbConstraint.TIENG_VIET };

	public PhoneListDao(Context context) {
		super(context);
	}

	public void deleteAll() {
		this.db.delete(tableName, null, null);
	}

	public long insertRow(ContactItem rowData) {
		ContentValues values = new ContentValues();
		values.put(DbConstraint.CLASS, rowData.getmClass());
		values.put(DbConstraint.CLASS_DETAILS, rowData.getmClassDetails());
		values.put(DbConstraint.STT, rowData.getStt());
		values.put(DbConstraint.NAME, rowData.getContactName());
		values.put(DbConstraint.PHONES, rowData.getPhoneNumber());
		values.put(DbConstraint.TOAN, rowData.getmToan());
		values.put(DbConstraint.TIENG_VIET, rowData.mTiengViet);

		return db.insertOrThrow(tableName, null, values);
	}

	public long updateRow(ContactItem rowData, String sTT) {
		ContentValues values = new ContentValues();
		values.put(DbConstraint.NAME, rowData.getContactName());
		values.put(DbConstraint.PHONES, rowData.getPhoneNumber());

		// return db.update(tableName, values, null, null);
		return db.update(tableName, values, DbConstraint.STT + " = ?",
				new String[] { sTT });
	}

	public long deleteRow(ContactItem rowData) {
		return db.delete(tableName, DbConstraint.STT + " = ?",
				new String[] { rowData.getStt() });
	}

	public List<ContactItem> selectAll() {
		List<ContactItem> result = new ArrayList<ContactItem>();
		ContactItem rowData = null;
		Cursor cursor = null;
		try {

			cursor = this.db.query(tableName, mColumns, null, null, null, null,
					null);

			if (cursor.moveToFirst()) {
				do {
					rowData = new ContactItem();
					rowData.setStt(cursor.getString(cursor
							.getColumnIndex(DbConstraint.STT)));
					rowData.setPhoneNumber(cursor.getString(cursor
							.getColumnIndex(DbConstraint.PHONES)));
					rowData.setmToan(cursor.getString(cursor
							.getColumnIndex(DbConstraint.TOAN)));
					rowData.setmTiengViet(cursor.getString(cursor
							.getColumnIndex(DbConstraint.TIENG_VIET)));
					result.add(rowData);
				} while (cursor.moveToNext());
			}

		} finally {
			if (cursor != null)
				cursor.close();
		}

		return result;
	}

	public List<ContactItem> selectForClassDetails(String mClassDetails) {
		List<ContactItem> result = new ArrayList<ContactItem>();
		ContactItem rowData = null;
		Cursor cursor = null;
		try {

			cursor = this.db.query(tableName, mColumns,
					DbConstraint.CLASS_DETAILS + " = ?",
					new String[] { mClassDetails }, null, null, null);

			if (cursor.moveToFirst()) {
				do {
					rowData = new ContactItem();
					rowData.setStt(cursor.getString(cursor
							.getColumnIndex(DbConstraint.STT)));
					rowData.setContactName(cursor.getString(cursor
							.getColumnIndex(DbConstraint.NAME)));
					rowData.setPhoneNumber(cursor.getString(cursor
							.getColumnIndex(DbConstraint.PHONES)));
					rowData.setmToan(cursor.getString(cursor
							.getColumnIndex(DbConstraint.TOAN)));
					rowData.setmTiengViet(cursor.getString(cursor
							.getColumnIndex(DbConstraint.TIENG_VIET)));
					result.add(rowData);
				} while (cursor.moveToNext());
			}

		} finally {
			if (cursor != null)
				cursor.close();
		}

		return result;
	}

}
