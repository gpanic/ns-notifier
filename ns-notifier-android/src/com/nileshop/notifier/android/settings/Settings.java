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
	
	public static void setTwitterConsumerKey(Context context, String s) {
		putString(context, Preference.TWITTER_CONSUMER_KEY, s);
	}
	
	public static String getTwitterConsumerKey(Context context) {
		return getString(context, Preference.TWITTER_CONSUMER_KEY);
	}
	
	public static void setTwitterConsumerSecret(Context context, String s) {
		putString(context, Preference.TWITTER_CONSUMER_SECRET, s);
	}
	
	public static String getTwitterConsumerSecret(Context context) {
		return getString(context, Preference.TWITTER_CONSUMER_SECRET);
	}
	
	public static void setLastCheck(Context context, Long l) {
		putLong(context, Preference.LAST_CHECK, l);
	}
	
	public static long getLastCheck(Context context) {
		return getLong(context, Preference.LAST_CHECK);
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
	
	public static void putString(Context context, Preference pref, String s) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(pref.getName(), s);
		editor.commit();
	}
	
	public static String getString(Context context, Preference pref) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		return settings.getString(pref.getName(), null);
	}
	
	public static void putLong(Context context, Preference pref, long l) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(pref.getName(), l);
		editor.commit();
	}
	
	public static long getLong(Context context, Preference pref) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		return settings.getLong(pref.getName(), -1);
	}

}
