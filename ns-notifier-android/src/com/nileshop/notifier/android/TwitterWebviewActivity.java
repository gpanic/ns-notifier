package com.nileshop.notifier.android;

import java.text.DecimalFormat;

import com.nileshop.notifier.android.settings.Settings;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class TwitterWebviewActivity extends Activity {
	
	private static final String TAG = "TwitterWebviewActivity";
	private WebView webView;
	private EditText pinEditText;
	private Button tweetButton2;
	private RequestToken requestToken;
	
	private String name;
	private String image;
	private double price;
	private String description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitterwebview);
		
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		image = intent.getStringExtra("image");
		price = intent.getDoubleExtra("price", 0);
		description = intent.getStringExtra("description");

		webView = (WebView)findViewById(R.id.twitterWebView);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		pinEditText = (EditText)findViewById(R.id.pinEditText);
		tweetButton2 = (Button)findViewById(R.id.tweetButton2);
		tweetButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "tweetButton2 onClick");
				try {
					String pinString = pinEditText.getText().toString();
					if (!pinString.equals("") && requestToken != null) {
						int pin = Integer.parseInt(pinString);
						new UpdateTwitterStatusTask().execute(pin);
					}
				} catch (NumberFormatException e) {
					Log.v(TAG, "PIN is not an umber");
				}
			}
		});
		new GetTwitterAuthURLTask().execute();
		Log.v(TAG, "onCreate finish");
	}
	
	private class GetTwitterAuthURLTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			Log.v(TAG, "GetTwitterAuthURLTask");
			Twitter twitter = TwitterFactory.getSingleton();
			try {
				requestToken = twitter.getOAuthRequestToken();
				String url = requestToken.getAuthorizationURL();
				Log.v(TAG, url);
				return url;
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			webView.loadUrl(result);
		}
		
	}
	
	private class UpdateTwitterStatusTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			Log.v(TAG, "UpdateTwitterStatusTask");
			int pin = params[0];
			Twitter twitter = TwitterFactory.getSingleton();
			try {
				AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, Integer.toString(pin));
				twitter.setOAuthAccessToken(accessToken);
				DecimalFormat df = new DecimalFormat("00.00");
				twitter.updateStatus(name + " is now available at Nile Shop for just " + df.format(price) + "\u20ac! " + image);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
		}
		
	}

}
