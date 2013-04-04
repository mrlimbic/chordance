package com.jdiligence.chordance;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ButtonAdapter extends BaseAdapter {
	private Context mContext;
	private String[] buttons = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII" };
	private SoundManager mSoundManager;
	private int[] intervals = {0, 2, 4, 5, 7, 9, 11, 12};

	public ButtonAdapter(Context c, SoundManager s) {
		mContext = c;
		mSoundManager = s;
	}

	public int getCount() {
		return buttons.length;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		Button tb;
		if (convertView == null) {
			tb = new Button(mContext);
			tb.setLayoutParams(new GridView.LayoutParams(85, 85));
			tb.setPadding(8, 8, 8, 8);
		} else {
			tb = (Button) convertView;
		}
		tb.setText(buttons[position]);
		tb.setId(position);
		tb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO Make this chords
				mSoundManager.playNote(intervals[position]);
			}
		});
		
		
		return tb;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}