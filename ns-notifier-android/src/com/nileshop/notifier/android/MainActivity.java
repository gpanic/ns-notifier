package com.nileshop.notifier.android;

import java.util.Calendar;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.nileshop.notifier.android.service.NotificationService;
import com.nileshop.notifier.android.settings.Settings;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private static final int INTENT_ID = 287892;
	private static final int TIMER_INTERVAL = 3;
	
	private ToggleButton tb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v(TAG, "onCreate");
		if (Settings.getTwitterConsumerKey(getApplicationContext()) == null || Settings.getTwitterConsumerSecret(getApplicationContext()) == null) {
			Settings.setTwitterConsumerKey(getApplicationContext(), "9oSODV3h3afPfHhdhUyWQ");
			Settings.setTwitterConsumerSecret(getApplicationContext(), "sZ1Ixlo0yKdXJ5GyTVw4dykztnlgtBERz7eL4kK6Q9U");
		}
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			twitter.setOAuthConsumer("9oSODV3h3afPfHhdhUyWQ", "sZ1Ixlo0yKdXJ5GyTVw4dykztnlgtBERz7eL4kK6Q9U");
		} catch (IllegalStateException e) {
			Log.v(TAG, "OAuthConsumer already set");
		}
		tb = (ToggleButton) findViewById(R.id.serviceToggleButton);
		tb.setChecked(isNotificationServiceRunning());
		tb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (tb.isChecked()) {
					startNotificationService();
				} else {
					stopNotificationService();
				}
			}
		});
		Log.v(TAG, "onCreate finish");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	private void startNotificationService() {
		Calendar c = Calendar.getInstance();
		Intent intent = new Intent(getApplicationContext(), NotificationService.class);
		PendingIntent pintent = PendingIntent.getService(getApplicationContext(), INTENT_ID, intent, 0);
		AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), TIMER_INTERVAL * 1000, pintent);
		Settings.setServiceRunning(getApplicationContext(), true);
	}
	
	private void stopNotificationService() {
		Intent intent = new Intent(getApplicationContext(), NotificationService.class);
		PendingIntent pintent = PendingIntent.getService(getApplicationContext(), INTENT_ID, intent, 0);
		AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pintent);
		Settings.setServiceRunning(getApplicationContext(), false);
	}
	
	private boolean isNotificationServiceRunning() {
		return Settings.isServiceRunning(getApplicationContext());
	}

}
