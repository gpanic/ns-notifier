package com.nileshop.notifier.android.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.nileshop.notifier.android.MainActivity;
import com.nileshop.notifier.android.ProductViewActivity;
import com.nileshop.notifier.android.R;
import com.nileshop.notifier.android.entity.Product;
import com.nileshop.notifier.android.json.NSParser;
import com.nileshop.notifier.android.settings.Settings;
import com.nileshop.notifier.android.utility.BitmapHelper;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends IntentService {
	
	private static final String TAG = "NotificationService";
	
	public NotificationService() {
		super("NotificationService");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v(TAG, "onHandleIntent");
		try {
			Date d = new Date();
			Log.v(TAG, Long.toString(d.getTime()));
			new GetProductsTask().execute(new URL("http://10.0.2.2:7659/ns-notifier-ws/rest/products/"));
			/*
			if (Settings.getLastCheck(getApplicationContext()) != -1) {
				long check = Settings.getLastCheck(getApplicationContext());
				new GetProductsTask().execute(new URL("http://10.0.2.2:7659/ns-notifier-ws/rest/products/new/" + check));
				Settings.setLastCheck(getApplicationContext(), d.getTime());
			} else {
				Settings.setLastCheck(getApplicationContext(), d.getTime());
			}*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
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
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String output;
					while ((output = br.readLine()) != null) {
						sb.append(output);
					}
					String json = sb.toString();
					List<Product> products = NSParser.productsFromJSON(json);
					if (products.size() > 0) {
						Log.v(TAG, products.get(0).getAdded().toString());
						Bitmap image = BitmapHelper.getBitmapFromURL(products.get(0).getImage());
						Bitmap scaledImage = Bitmap.createScaledBitmap(image, 100, 100, false);
						createNotification(products.get(0), scaledImage);
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "FINISHED";
		}
		
	}
	
	private void createNotification(Product product, Bitmap image) {
		DecimalFormat df = new DecimalFormat("0.00");
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
		.setSmallIcon(R.drawable.ic_stat_notification)
		.setLargeIcon(image)
		.setContentTitle(product.getName())
		.setContentText(df.format(product.getPrice())+'\u20ac')
		.setAutoCancel(true);
		
		Intent intent = new Intent(this, ProductViewActivity.class);
		intent.putExtra("name", product.getName());
		intent.putExtra("image", product.getImage());
		intent.putExtra("price", product.getPrice());
		intent.putExtra("description", product.getDescription());
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(intent);
		PendingIntent pintent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pintent);
		NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(15, builder.build());
	}

}
