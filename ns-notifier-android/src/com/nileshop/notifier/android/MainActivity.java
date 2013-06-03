package com.nileshop.notifier.android;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nileshop.notifier.android.entities.Product;
import com.nileshop.notifier.android.json.NSParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v("MyActivity", "START");
		try {
			new GetProductsTask().execute(new URL("http://10.0.2.2:7659/ns-notifier-ws/rest/products"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Log.v("MyActivity", "END");
	}
	
	private class GetProductsTask extends AsyncTask<URL, Void, String> {

		@Override
		protected String doInBackground(URL... params) {
			URL url = params[0];
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					Log.v("MyActivity", "NOT 200");
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String output;
					while ((output = br.readLine()) != null) {
						sb.append(output);
					}
					String json = sb.toString();
					List<Product> products = NSParser.productsFromJSON(json);
					Log.v("MyActivity", products.toString());
					Log.v("MyActivity", "FINISHED");
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "FINISHED";
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
