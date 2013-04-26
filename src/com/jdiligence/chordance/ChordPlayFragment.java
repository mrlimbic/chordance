package com.jdiligence.chordance;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ChordPlayFragment extends Fragment {
	private Button[] buttons;
	private SoundManager soundManager;
	
	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.chordplay_fragment, container, false);
		    
		buttons = new Button[] {
				(Button)view.findViewById(R.id.chord1), (Button)view.findViewById(R.id.chord2), (Button)view.findViewById(R.id.chord3), (Button)view.findViewById(R.id.chord4),
				(Button)view.findViewById(R.id.chord5), (Button)view.findViewById(R.id.chord6), (Button)view.findViewById(R.id.chord7)};
		
		String[] chords = getResources().getStringArray(R.array.chords);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(chords[i]);
			buttons[i].setOnClickListener(new ChordListener(i));
		}

		return view;
	}
	
	class ChordListener implements OnClickListener {
		int chord;
		
		ChordListener(int chord) {
			this.chord = chord;
		}
		
		@Override
		public void onClick(View v) {
			soundManager.playChord(chord);
		}
	}

}
