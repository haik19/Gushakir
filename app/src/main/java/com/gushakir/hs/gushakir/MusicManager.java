package com.gushakir.hs.gushakir;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

/**
 * Created by comp on 14.03.2018.
 */

public class MusicManager {

	private static MusicManager musicManager;
	private MediaPlayer hideBlockMusic;


	private MusicManager(Context context) {
		hideBlockMusic = MediaPlayer.create(context, R.raw.buble_sound_2);
	}

	public void playHideBlockMusic() {
		if (hideBlockMusic != null) {
			hideBlockMusic.start();
		}
	}

	public static MusicManager getMusicManager(Context context) {
		if (musicManager == null) {
			musicManager = new MusicManager(context);
		}
		return musicManager;
	}
}
