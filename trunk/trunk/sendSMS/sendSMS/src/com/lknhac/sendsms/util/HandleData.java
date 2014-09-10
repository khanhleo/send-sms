package com.lknhac.sendsms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;

public class HandleData {
	public static void insertData(Context context, String fileName, String mClass, String mClassDetails) throws IOException{
    	FileReader file = new FileReader(fileName);
    	BufferedReader buffer = new BufferedReader(file);
    	String line = "";

    	PhoneListDao phoneListDao = new PhoneListDao(context);
    	while ((line = buffer.readLine()) != null) {
    	    String[] str = line.split(",");
    	    
    	    
    	    ContactItem contactItem = new ContactItem();
    	    
    	    
    	    contactItem.setStt(str[0]);
    	    contactItem.setPhoneNumber(str[2]);  
    	    contactItem.setmClass(mClass);
    	    contactItem.setmClassDetails(mClassDetails);
    	    phoneListDao.insertRow(contactItem);   	    
    	}
    	buffer.close();
    	phoneListDao.close();		
    }
	public static String getFilePath(Context context, String mClass, String mClassDetails){
		 File f = new File(context.getCacheDir()+"/"+mClassDetails+".tsv");
		  if (!f.exists()) try {

		    InputStream is = context.getAssets().open("savefile"+"/"+"Khoi"+mClass+"/"+mClassDetails+".csv");
		    int size = is.available();
		    byte[] buffer = new byte[size];
		    is.read(buffer);
		    is.close();


		    FileOutputStream fos = new FileOutputStream(f);
		    fos.write(buffer);
		    fos.close();
		  } catch (Exception e) { throw new RuntimeException(e); }

		  return f.getAbsolutePath();
	}
}
