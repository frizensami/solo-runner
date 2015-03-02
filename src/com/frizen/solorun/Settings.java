package com.frizen.solorun;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings);
	    
	    final SharedPreferences settings =  getSharedPreferences("Settings", MODE_PRIVATE);  
	    
	    int min = settings.getInt("min", 20000) / 1000;
	    int max = settings.getInt("max", 25000) / 1000;
	    int min1 = settings.getInt("min1", 2000) / 1000;
	    int max1 = settings.getInt("max1", 5000) / 1000;
	    
	    final EditText s1 = (EditText) findViewById(R.id.editText1);
	    final EditText s2 = (EditText) findViewById(R.id.editText2);
	    final EditText s3 = (EditText) findViewById(R.id.editText3);
	    final EditText s4 = (EditText) findViewById(R.id.editText4);
	    
	    s1.setText(min + "");
	    s2.setText(max + "");
	    s3.setText(min1 + "");
	    s4.setText(max1 + "");
	    
	    
	    
	   
	    
	    Button save = (Button) findViewById(R.id.button1);
	    save.setOnClickListener((new OnClickListener() {
	    	
	    	public void onClick(View v)
	    	{
	    		try{
	    			
	    			SharedPreferences.Editor eSettings = settings.edit();
		    		eSettings.putInt("min",(Integer.parseInt(s1.getText().toString()) * 1000));
		    		eSettings.putInt("max",(Integer.parseInt(s2.getText().toString()) * 1000));
		    		eSettings.putInt("min1",(Integer.parseInt(s3.getText().toString()) * 1000));
		    		eSettings.putInt("max1",(Integer.parseInt(s4.getText().toString()) * 1000));
		    		eSettings.commit();
		    		
		    		Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_SHORT).show()	;

	    			
	    		}
	    		catch (Exception e){
	    			Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
	    		}
	    			    	}
	    }));
	    	
	  
	    
	    
	    
	    
	    
	    
	}

}
