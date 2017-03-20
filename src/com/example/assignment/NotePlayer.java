package com.example.assignment;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class NotePlayer extends AsyncTask<Void, Void, Void> {

	
	Context context;
	private MediaPlayer player;
	HashMap mp;
	String notes[];
	volatile int id;
	
	
	public NotePlayer(Context context, HashMap mp, String notes[]){
		this.context=context;
		this.mp=mp;
		this.notes=notes;
		id=0;
		
	}
	

	@Override
	protected void onPreExecute() {
		try {
			while(notes[id]=="."&& id<notes.length){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				id++;
				
			}
			player=MediaPlayer.create(context, (Integer)mp.get(notes[id]));
			setNextNote(player);
			super.onPreExecute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setNextNote(MediaPlayer player){
		player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer p) {
				try {
					if(!isCancelled()){id++;
					while(id<notes.length && notes[id].equals(".")){
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						id++;
						
					}
					if(id<notes.length){
						p.release();
						p=MediaPlayer.create(context, (Integer)mp.get(notes[id]));
						setNextNote(p);
						p.start();
					}
					else{
						p.stop();
						p.release();
						p=null;
					}
					}
					else{
						p.stop();
						p.release();
						p=null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	protected Void doInBackground(Void... arg0) {
		
		
		try {
			player.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected void onCancelled(Void result) {
		Log.e("Array","stopping");
		player.stop();
		player.release();
		player=null;
	}


	
	


}
