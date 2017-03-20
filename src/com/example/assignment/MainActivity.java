package com.example.assignment;


import java.util.HashMap;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private EditText notes;
	private Button play, stop;
	private NotePlayer audio;
	HashMap mp = new HashMap();
    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes= (EditText)findViewById(R.id.etNotes);
        play= (Button) findViewById(R.id.bPlay);
        stop= (Button) findViewById(R.id.bStop);
        mapAudioFiles();
        
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
	
	public void mapAudioFiles(){
		mp.put("A1",R.raw.a1);
		mp.put("A1s",R.raw.a1s);
		mp.put("B1",R.raw.b1);
		mp.put("C1",R.raw.c1);
		mp.put("C1s",R.raw.c1s);
		mp.put("C2",R.raw.c2);
		mp.put("D1",R.raw.d1);
		mp.put("D1s",R.raw.d1s);
		mp.put("E1",R.raw.e1);
		mp.put("F1",R.raw.f1);
		mp.put("F1s",R.raw.f1s);
		mp.put("G1",R.raw.g1);
		mp.put("G1s",R.raw.g1s);
	}

	@Override
	public void onClick(View v) {
		
		if(v==play){
			String[] note= notes.getText().toString().split(" ");
			audio= new NotePlayer(this, mp, note);
			Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
			Log.e("Array",String.valueOf( note.length));
			audio.execute();
			
			
			
		}
		else if(v==stop){
			if(audio!=null){
				if(!audio.isCancelled()){
					audio.cancel(true);
					Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();
					audio=null;
				}
			}
		}
		
	}
	
	
	

}
