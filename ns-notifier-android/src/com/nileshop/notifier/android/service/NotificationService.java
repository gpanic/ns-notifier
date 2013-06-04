package com.nileshop.notifier.android.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.nileshop.notifier.android.entity.Product;
import com.nileshop.notifier.android.json.NSParser;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class NotificationService extends IntentService {
	
	private static final String TAG = "NotificationService";
	
	public NotificationService() {
		super("NotificationService");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v(TAG, "onHandleIntent");
		try {
			new GetProductsTask().execute(new URL("http://10.0.2.2:7659/ns-notifier-ws/rest/products"));
			Log.v(TAG, "executed");
		} catch (MalformedURLException e) {
			Log.v(TAG, "failed");
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}
	
	private class GetProductsTask extends AsyncTask<URL, Void, String> {

		@Override
		protected String doInBackground(URL... params) {
			URL url = params[0];
			Log.v(TAG, "GetProductsTask");
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					Log.v(TAG, "CONNECTION FAILED");
				} else {
					Log.v(TAG, "HERE2");
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String output;
					while ((output = br.readLine()) != null) {
						sb.append(output);
					}
					String json = sb.toString();
					List<Product> products = NSParser.productsFromJSON(json);
					Log.v(TAG, products.get(0).getAdded().toString());
				}
				Log.v(TAG, "HERE2");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "FINISHED";
		}
	}

}
