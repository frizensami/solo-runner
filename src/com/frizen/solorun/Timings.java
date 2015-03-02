package com.frizen.solorun;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Timings extends Activity implements AdapterView.OnItemSelectedListener{

	 
    ArrayList titlearray = new ArrayList();
	ArrayList harray = new ArrayList();
	ArrayList marray = new ArrayList();
	ArrayList sarray = new ArrayList();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.timings);
	    
	    
	    populateSpinner();
	    
	    Button saveTiming = (Button) findViewById(R.id.buttonSaveTiming);
	   
	    saveTiming.setOnClickListener(new OnClickListener() {
	    	
	    	public void onClick(View v)
	    	{
	    		try
	    		{
	    			EditText h = (EditText) findViewById(R.id.EditTextH);
	    			EditText m = (EditText) findViewById(R.id.EditTextM);
	    			EditText s = (EditText) findViewById(R.id.EditTextS);
	    			EditText title = (EditText) findViewById(R.id.editTextTitle);
	    			
	    			File root = new File(Environment.getExternalStorageDirectory() + "/solorun/");
				
					 if (!root.exists()) {
		    	            root.mkdirs();
		    	        }
		    	        
		    	        File gpxfile = new File(root, "timings.txt");
		    	        FileWriter writer = new FileWriter(gpxfile, true); //append
		    	        Log.w("Writer", "FileWriter Done");
		    	        
		    	        String hours = h.getText().toString();
		    	        if (hours.length() == 0)
		    	        	hours = "0";
		    	        String min = m.getText().toString();
		    	        if (min.length() == 0)
		    	        	min = "0";
		    	        String sec = s.getText().toString();
		    	        if (sec.length() == 0)
		    	        	sec = "0";
		    	        String titles = title.getText().toString();
		    	        if (titles == "")
		    	        	titles = " ";
		    	        
		    	        
		    	        writer.write(hours + "\r\n");
		    	        writer.write(min + "\r\n");
		    	        writer.write(sec + "\r\n");
		    	        writer.write(titles + "\r\n");
		    	        
		    	        writer.close();
		    	        
		    	        populateSpinner();
					 
	    		}
	    		catch (Exception ex)
	    		{
	    			Log.w("Ohno", "error: " + ex.getMessage());
	    		}
	    		
	    		
	    		
	    		
	    	}
	    	
	    });
		
	    
	    Button clear = (Button) findViewById(R.id.ButtonClear);
	    clear.setOnClickListener(new OnClickListener(){
	    	
	    	public void onClick(View v)
	    	{
	    		try
	    		{
	    			File root = new File(Environment.getExternalStorageDirectory() + "/solorun/");
					
					 if (!root.exists()) {
		    	            root.mkdirs();
		    	        }
		    	        
		    	        File gpxfile = new File(root, "timings.txt");
		    	        FileWriter writer = new FileWriter(gpxfile, false); //do not append
		    	        writer.write("");
		    	        writer.close();
		    	        
		    	        titlearray = new ArrayList();
		    			harray = new ArrayList();
		    			marray = new ArrayList();
		    			sarray = new ArrayList();
		    			TextView timing = (TextView) findViewById(R.id.textViewTiming);
   			         	timing.setText("");
		    			populateSpinner();
	    		}
	    		catch (Exception e)
	    		{
	    			Log.w("FileNotFound", "Error: " + e.getMessage());
	    			 
	    		    
	    		}
	    	}
	    	
	    });
	 
    		
    		
	    }
	
	public void populateSpinner()
	{

		
	       try{
	    	   
	    	   
	    	   File root = new File(Environment.getExternalStorageDirectory() + "/solorun/", "timings.txt");
			   Scanner in  = new Scanner(root);
			   
			   
			   int counter = 0;
			   while(in.hasNext())
				{
					
					harray.add(in.nextLine());
					marray.add(in.nextLine());
					sarray.add(in.nextLine());
					titlearray.add(in.nextLine());
					counter++;
				}				
	    		in.close();
	    		
				
 		}
 		
 		catch (Exception e){//Catch exception if any
 			
 			  Log.w("Ohno", "error: " + e.getMessage());
 			  }
	        
 		
 		
 		//Toast.makeText(getApplicationContext(),namearray[1], Toast.LENGTH_SHORT).show();
	        
 		
 		Spinner s = (Spinner)findViewById(R.id.spinner1);
 		
 		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, titlearray); 
 		s.setOnItemSelectedListener(this);
 		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 		s.setAdapter(adapter);
	}
	

    	/*	final ArrayList namearray2 = namearray;
    		final ArrayList amountarray2 = amountarray;
    		final ArrayList datearray2 = datearray;
    		final ArrayList montharray2 = montharray;
    		final ArrayList yeararray2 = yeararray; */
    		 
    		 public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)  {
    		        	
    		        	 String selected = parent.getItemAtPosition(pos).toString();
    			          
    	  		          int index = titlearray.indexOf(selected);
    	  		          //Toast.makeText(getApplicationContext(),"index: " + index,Toast.LENGTH_SHORT).show();
    			          TextView title = (TextView) findViewById(R.id.textViewStatus);
    			          TextView timing = (TextView) findViewById(R.id.textViewTiming);
    			         
    			         
    			          timing.setText("Timing - " + harray.get(index).toString() + " H " + 
    			        		  marray.get(index).toString() + " Min " +
    			        		  sarray.get(index).toString() + " Sec " );
    			          title.setText(selected.toString());
    		    }
    		  public void onNothingSelected(AdapterView<?> parent) {
   		       
  		    }
  		
	}



