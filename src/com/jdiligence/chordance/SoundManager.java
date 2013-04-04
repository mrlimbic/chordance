package com.jdiligence.chordance;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;

public class SoundManager {

	private SoundPool mSoundPool;
	private AudioManager mAudioManager;
	private Context mContext;
	
	private int[] rids = new int[] { 
			R.raw.c3, R.raw.d3b, R.raw.d3, R.raw.eb3, R.raw.e3, R.raw.f3, R.raw.g3b, R.raw.g3, R.raw.a3b, R.raw.a3, R.raw.b3b, R.raw.b3,
			R.raw.c4
		};
	
	private int[] sids;

	public void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
		
		// load
		sids = new int[rids.length];
		mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
		    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		       Log.i("load", "load " + sampleId + ":" + status);
		    }
		});
		
		for (int i = 0; i < rids.length; i++) {
			sids[i] = mSoundPool.load(mContext, rids[i], 1);
		}
	}

	// A chord is an array of intervals
	public void playChord(int[] chord) {
		// chords sound terrible unless we stop other notes first
		mSoundPool.autoPause();
		for (int i = 0; i < chord.length; i++)
			playNote(chord[i]);
	}
	
	// All notes are intervals upwards from C3
	public void playNote(int interval) {
		if (interval >= rids.length)
			interval = interval % 12; // prevent playing notes we don't have
		
		int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	    int streamId = mSoundPool.play(sids[interval], streamVolume, streamVolume, 1, 0, 1f);			
	}
}
