package com.nileshop.notifier.android;

import java.net.URL;
import java.text.DecimalFormat;

import com.nileshop.notifier.android.utility.BitmapHelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductViewActivity extends Activity {
	private static final String TAG = "ProductViewActivity";
	
	private ImageView productImageView; 
	private String name;
	private String image;
	private double price;
	private String description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productview);
		
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		image = intent.getStringExtra("image");
		price = intent.getDoubleExtra("price", 0);
		description = intent.getStringExtra("description");
		
		TextView productTitleTextView = (TextView)findViewById(R.id.productTitleTextView);
		productTitleTextView.setText(name);
		productImageView = (ImageView) findViewById(R.id.productImageView);
		TextView productPriceTextView = (TextView)findViewById(R.id.productPriceTextView);
		DecimalFormat df = new DecimalFormat("00.00");
		productPriceTextView.setText(df.format(price)+"\u20ac");
		TextView productDescriptionTextView = (TextView)findViewById(R.id.productDescriptionTextView);
		productDescriptionTextView.setText(description);
		Button tweetButton = (Button)findViewById(R.id.tweetButton);
		tweetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), TwitterWebviewActivity.class);
				i.putExtra("name", name);
				i.putExtra("image", image);
				i.putExtra("price", price);
				i.putExtra("description", description);
				startActivity(i);
			}
		});
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
