package com.lknhac.sendsms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lknhac.sendsms.data.PhoneListDao;
import com.lknhac.sendsms.object.ContactItem;


public class MainActivity extends Activity {

	private String fileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        // test revisions
    }
    
    private void checkIsHasData(){
    	PhoneListDao summaryDao = new PhoneListDao(MainActivity.this);
    	List<ContactItem> listSummary = summaryDao.selectAll();
    }
    
    public void clickInsertData(View view) {
    	File root = Environment.getExternalStorageDirectory();
    	fileName = root.getAbsolutePath() +"/SendSMS"+"/phonelist.csv";
    	try {
			insertData(fileName);
			Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
		}
    }
    
    public void clickClass(View view){
    	Intent intent = new Intent(MainActivity.this, ListClassActitvity.class);
		startActivity(intent);
    }
	public void clickCheckData(View view){
		Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
		startActivity(intent);
	}
	
	public void clickClearData(View view){
		PhoneListDao phoneListDao = new PhoneListDao(getApplicationContext());
		phoneListDao.deleteAll();
		phoneListDao.close();
	}
	
	public void clickSend(View view){
		Intent intent = new Intent(MainActivity.this, SendSMSActivity.class);
		startActivity(intent);
	}
    private void insertData(String fileName) throws IOException{
    	FileReader file = new FileReader(fileName);
    	BufferedReader buffer = new BufferedReader(file);
    	String line = "";

    	PhoneListDao phoneListDao = new PhoneListDao(getApplicationContext());
    	while ((line = buffer.readLine()) != null) {
    	    String[] str = line.split(",");
    	    
    	    
    	    ContactItem contactItem = new ContactItem();
    	    
    	    contactItem.setmClassDetails("6.1");
    	    contactItem.setStt(str[0]);
    	    contactItem.setPhoneNumber(str[1]);  	    
    	    phoneListDao.insertRow(contactItem);   	    
    	}
    	buffer.close();
    	phoneListDao.close();		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
