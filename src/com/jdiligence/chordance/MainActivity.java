package com.jdiligence.chordance;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	private static final String KEY = "key";
	private static final String SCALE = "scale";
	private static final String MODE = "mode";
	private static final String TONES = "tones";
	private static final String INVERSION = "inversion";
	
	private SharedPreferences prefs;
	
	private SoundManager mSoundManager;

	private Spinner key;
	private Spinner scale;
	private Spinner mode;
	private Spinner tones;
	private Spinner inversion;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		mSoundManager = new SoundManager();
		mSoundManager.initSounds(getBaseContext());
		
		key = (Spinner)this.findViewById(R.id.key);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.keys, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		key.setAdapter(adapter);
		key.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mSoundManager.setKey(key.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}});
				
		scale = (Spinner)this.findViewById(R.id.scale);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.scales, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		scale.setAdapter(adapter2);
		scale.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mSoundManager.setScale(scale.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}});

		mode = (Spinner)this.findViewById(R.id.mode);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
		        R.array.modes, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mode.setAdapter(adapter3);
		mode.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mSoundManager.setMode(mode.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}});

		tones = (Spinner)this.findViewById(R.id.tones);
		ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
		        R.array.tones, android.R.layout.simple_spinner_item);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tones.setAdapter(adapter5);
		tones.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mSoundManager.setTones(tones.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}});


		inversion = (Spinner)this.findViewById(R.id.inversion);
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
		        R.array.inversions, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		inversion.setAdapter(adapter4);
		inversion.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mSoundManager.setInversion(inversion.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}});
		
		GridView chordGrid = (GridView) this.findViewById(R.id.chords);
		chordGrid.setAdapter(new ChordAdapter(this, mSoundManager));
		
		prefs = getPreferences(Activity.MODE_PRIVATE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(KEY, key.getSelectedItemPosition());
		editor.putInt(SCALE, scale.getSelectedItemPosition());
		editor.putInt(MODE, mode.getSelectedItemPosition());
		editor.putInt(TONES, tones.getSelectedItemPosition());
		editor.putInt(INVERSION, inversion.getSelectedItemPosition());
		
		editor.commit();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		key.setSelection(prefs.getInt(KEY, 0));
		scale.setSelection(prefs.getInt(SCALE, 0));
		mode.setSelection(prefs.getInt(MODE, 0));
		tones.setSelection(prefs.getInt(TONES, 0));
		inversion.setSelection(prefs.getInt(INVERSION, 0));		
	}
}
