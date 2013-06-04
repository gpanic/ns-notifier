package com.nileshop.notifier.android;

import java.util.Calendar;
import com.nileshop.notifier.android.service.NotificationService;

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
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v(TAG, "onCreate");
		Button startServiceButton = (Button) findViewById(R.id.startServiceButton);
		startServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "startServiceButton");
				Calendar c = Calendar.getInstance();
				Intent intent = new Intent(v.getContext(), NotificationService.class);
				PendingIntent pintent = PendingIntent.getService(v.getContext(), 0, intent, 0);
				AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				alarm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5*1000, pintent);
			}
		});
		Button stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
		stopServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "stopServiceButton");
				Intent intent = new Intent(v.getContext(), NotificationService.class);
				PendingIntent pintent = PendingIntent.getService(v.getContext(), 0, intent, 0);
				AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				alarm.cancel(pintent);
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

}
