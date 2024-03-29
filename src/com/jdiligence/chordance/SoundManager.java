package com.jdiligence.chordance;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {

	private SoundPool mSoundPool;
	private AudioManager mAudioManager;
	private Context mContext;
	
	final private int[][] SCALES = {{0, 2, 4, 5, 7, 9, 11}, {0, 2, 4, 5, 7, 9, 10}, {0, 2, 3, 5, 7, 8, 10}, {0, 2, 3, 5, 7, 9, 11}, {0, 2, 3, 5, 7, 8, 11}};
	final private int[][] TONES = {{0, 2, 4}, {0, 2, 5}, {0, 2, 6}, {0, 2, 4, 6}, {0, 1, 4}, {0, 3, 4}};
	
	private int[] rids = new int[] { 
			R.raw.c3, R.raw.d3b, R.raw.d3, R.raw.e3b, R.raw.e3, R.raw.f3, R.raw.g3b, R.raw.g3, R.raw.a3b, R.raw.a3, R.raw.b3b, R.raw.b3,
			R.raw.c4, R.raw.d4b, R.raw.d4, R.raw.e4b, R.raw.e4, R.raw.f4, R.raw.g4b, R.raw.g4, R.raw.a4b, R.raw.a4, R.raw.b4b, R.raw.b4,
			R.raw.c5
		};
	
	private int[] sids;
	
	private int key;
	private int scale;
	private int mode;
	private int tones;
	private int inversion;

	public void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
//		mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
//	    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//	       Log.i("load", "load " + sampleId + ":" + status);
//	    }
//	});
		sids = new int[rids.length];
		for (int i = 0; i < rids.length; i++) {
			sids[i] = mSoundPool.load(mContext, rids[i], 1);
		}
	}

	public void playChord(int position) {
		int[] intervals = TONES[tones];
		int[] chord = new int[intervals.length];
		for (int i = 0; i < chord.length; i++) {
			int toff = intervals[i] + position + mode;
			int interval = SCALES[scale][toff % 7] + (12 * (toff / 7));
			chord[i] = interval + key + (i < inversion ? 12 : 0);
		}

		playChord(chord);
	}
	
	// A chord is an array of intervals from C3
	public void playChord(int[] chord) {
		boolean transpose = false;
		int lowest = chord[0];
		// Constrain the chord to play within our two octave sample range
		for (int i = 0; i < chord.length; i++) {
			if (chord[i] >= rids.length) {
				transpose = true; // gone over the top so transpose down
			}
			
			if (chord[i] < lowest) {
				lowest = chord[i]; // find lowest note
			}
		}
		
		if (transpose) {
			// we can pulldown only as many octaves as lowest note doesn't go below 0
			int octaves = lowest / 12;
			for (int i = 0; i < chord.length; i++) {
				chord[i] -= octaves * 12;
			}
		}
		
		// chords sound terrible unless we stop other notes first
		mSoundPool.autoPause();
		for (int i = 0; i < chord.length; i++)
			playNote(chord[i]);
	}
	
	// All notes are intervals upwards from C3
	public void playNote(int interval) {
		if (interval >= rids.length)
			interval = interval % 24; // prevent playing notes we don't have
		
		int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	    int streamId = mSoundPool.play(sids[interval], streamVolume, streamVolume, 1, 0, 1f);			
	}

	public int getInversion() {
		return inversion;
	}

	public void setInversion(int inversion) {
		this.inversion = inversion;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getTones() {
		return tones;
	}

	public void setTones(int tones) {
		this.tones = tones;
	}
	
}
