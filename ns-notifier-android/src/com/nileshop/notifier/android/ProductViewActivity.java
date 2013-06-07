package com.nileshop.notifier.android;

import java.net.URL;

import com.nileshop.notifier.android.utility.BitmapHelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductViewActivity extends Activity {
	private static final String TAG = "ProductViewActivity";
	
	private ImageView productImageView; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productview);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String image = intent.getStringExtra("image");
		
		TextView tv = (TextView)findViewById(R.id.productTitleTextView);
		tv.setText(name);
		productImageView = (ImageView) findViewById(R.id.productImageView);
		new SetImageTask().execute(image);
	}
	
	private class SetImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap image = BitmapHelper.getBitmapFromURL(params[0]);
			image = Bitmap.createScaledBitmap(image, 250, 250, false);
			return image;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			productImageView.setImageBitmap(result);
		}
		
	}

}
