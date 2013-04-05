package com.jdiligence.chordance;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import com.jdiligence.chordance.R;

public class MainActivity extends Activity {
	
	private SoundManager mSoundManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		mSoundManager = new SoundManager();
		mSoundManager.initSounds(getBaseContext());

		GridView chordGrid = (GridView) this.findViewById(R.id.chords);
		chordGrid.setAdapter(new ChordAdapter(this, mSoundManager));

		GridView inversionGrid = (GridView) this.findViewById(R.id.inversions);
		inversionGrid.setAdapter(new InversionAdapter(this, mSoundManager));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
