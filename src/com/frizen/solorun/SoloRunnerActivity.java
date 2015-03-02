package com.frizen.solorun;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


public class SoloRunnerActivity extends Activity implements TextToSpeech.OnInitListener {
	
	 private TextToSpeech mTts;
	    // This code can be any value you want, its just a checksum.
	    private static final int MY_DATA_CHECK_CODE = 1234;
	    
    
	       
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

	  
        
        ImageView go = (ImageView) findViewById(R.id.imggo);
        
        go.setOnClickListener(new OnClickListener() {
        	   
        	   public void onClick(View v) {
        	      
        		// Fire off an intent to check if a TTS engine is installed
        	        Intent checkIntent = new Intent();
        	        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        	        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        	 
        		   
        		   
        	   }
        	  });
    }
    
    
    
    /**
     * Executed when a new TTS is instantiated. Some static text is spoken via TTS here.
     * @param i
     */
    public void onInit(int i)
    {
    	
    	 SharedPreferences settings =  getSharedPreferences("Settings", MODE_PRIVATE);  
  	    
  	    int min = settings.getInt("min", 20000);
  	    int max = settings.getInt("max", 25000);
  	    int min1 = settings.getInt("min1", 2000);
  	    int max1 = settings.getInt("max1", 5000);
        mTts.speak("On Your Marks!!!",
                TextToSpeech.QUEUE_ADD,  // Drop all pending entries in the playback queue.
                null);
        final TextView status = (TextView) findViewById(R.id.textViewStatus);
        
        status.setVisibility(View.VISIBLE);
        status.setText("ON YOUR MARKS");
        
 
    	Random rand = new Random();
    	final int randi = rand.nextInt(max - min + 1) + min;
    	//Toast.makeText(getApplicationContext(), "FIRSTPSE" + randi, Toast.LENGTH_SHORT).show();
        
        Handler handler = new Handler(); 
        
        handler.postDelayed(new Runnable() { 
            public void run() { 
            	
            	mTts.speak("get set!!!!.",
                        TextToSpeech.QUEUE_ADD,  // Drop all pending entries in the playback queue.
                        null);
            	status.setText("GET SET");
            	 } 
       }, randi);  
        
       
        
    	Random rand1 = new Random();
    	final int randi1 = rand1.nextInt(max1 - min1 + 1) + min1;
    	
    	final int totalt = randi1 + randi;
    	//Toast.makeText(getApplicationContext(), "Secondpse=" + totalt, Toast.LENGTH_SHORT).show();
        
        
        handler.postDelayed(new Runnable() { 
            public void run() { 
            	//use wav	
            	MediaPlayer mediaPlayer = MediaPlayer.create(SoloRunnerActivity.this, R.raw.bangedit);
            	mediaPlayer.start(); // no need to call prepare(); create() does that for you
            	status.setText("GO!");
            	
            	/*
            	 mTts.speak("BANG!",
                         TextToSpeech.QUEUE_ADD,  // Drop all pending entries in the playback queue.
                         null); */
            	  } 
       }, totalt); 
        
       
    }
 
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            }
            else
            {
                // missing data, install it
            	AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SoloRunnerActivity.this);
            	 myAlertDialog.setTitle("Download");
	        	 myAlertDialog.setMessage("You do not have the Android Text-To" +
	        	 		"-Speech system installed yet. Pressing OK will take you" +
	        	 		"to download this package.");
	        	 myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        		 public void onClick(DialogInterface arg0, int arg1) {
	        			 Intent installIntent = new Intent();
	                     installIntent.setAction(
	                             TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
	                     startActivity(installIntent);
	        		 
	        			 
	        		 }
	        		
               
            }
	        	 
	        	 );
	        	 myAlertDialog.show();
            }
        }
    }
    
    @Override
    public void onDestroy()
    {
        // Don't forget to shutdown!
        if (mTts != null)
        {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
   
    
    




//MENU CREATOR

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    menu.add("Timings");
    menu.add("Settings");

   // Return true so that the menu gets displayed.
   return true;

}


	//HANDLE MENU PRESSES
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	

    // Handle item selection
    
	String title = item.getTitle().toString();

	if (title == "Timings")
	{
		
		Intent newIntent = new Intent(getApplicationContext(), Timings.class);
		startActivity(newIntent);

		
		return true;
	}
	
	if (title == "Settings")
	{

		Intent newIntent = new Intent(getApplicationContext(), Settings.class);
		startActivity(newIntent);
		return true;
    }
	
       
	else
		return super.onOptionsItemSelected(item);
            
	//returns the menu item name
	//Toast.makeText(getApplicationContext(), "Item Selected Name: " + item.getTitle(), Toast.LENGTH_SHORT).show();
	//return super.onOptionsItemSelected(item);
    }





}







