package com.nileshop.notifier.android.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
	
	private static final String PREFS_NAME = "ns_prefs";
	
	public static void setServiceRunning(Context context, boolean running) {
		putBoolean(context, Preference.SERVICE_RUNNING, running);
	}
	
	public static boolean isServiceRunning(Context context) {
		return getBoolean(context, Preference.SERVICE_RUNNING);
	}
	
	public static void putBoolean(Context context, Preference pref, boolean bool) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(pref.getName(), bool);
		editor.commit();
	}
	
	public static boolean getBoolean(Context context, Preference pref) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		return settings.getBoolean(pref.getName(), false);
	}

}
