package com.lknhac.sendsms.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactItem implements Serializable {
	private static final long serialVersionUID = -2572310233833415125L;
	
	public String mClass;
	public String mClassDetails;
	public String contactName;
	public String phoneNumber;
	public String stt;
	public List<String> numbers = new ArrayList<String>();
	public boolean isExist;
	
	
	public String getmClass() {
		return mClass;
	}
	public void setmClass(String mClass) {
		this.mClass = mClass;
	}
	public String getmClassDetails() {
		return mClassDetails;
	}
	public void setmClassDetails(String mClassDetails) {
		this.mClassDetails = mClassDetails;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public void setNumbers(String numberString) {
		numbers.addAll(Arrays.asList(numberString.split(",")));
	}
	
	public String getNumbersAsString() {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (String number : numbers) {
			if (i != 0) {
				builder.append(",");
			}
			builder.append(number);
			i++;
		}
		return builder.toString();
	}
	
	public ContactItem copy() {
		ContactItem result = new ContactItem();
		
		result.setContactName(contactName);
		result.setExist(isExist);
		result.setNumbers(getNumbers());
		
		return result;
	}
}