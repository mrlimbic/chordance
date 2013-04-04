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
	
	private int c3;
	boolean loaded = false;

	public void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
		
		// load
		c3 = mSoundPool.load(mContext, R.raw.c3, 1);
		mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
		    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		       loaded = true;
		       Log.i("load", "complete");
		    }
		});
	}

	public void playNote(int interval) {
		if (loaded) {
			int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	        int streamId = mSoundPool.play(c3, streamVolume, streamVolume, 1, 0, 1f);			
		}
	}
}
