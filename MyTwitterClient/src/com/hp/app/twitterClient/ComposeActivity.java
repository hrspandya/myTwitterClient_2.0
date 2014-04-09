package com.hp.app.twitterClient;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.twitterClient.R;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	
	public void postNewTweet(View v) {
		EditText tweetText = (EditText) findViewById(R.id.et_tweet);
		String tweetStatus = tweetText.getText().toString();
		final Context that = this;
		
		TwitterClientApp.getRestClient().postNewTweet(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject json) {
				Intent i = new Intent(that, TimelineActivity.class);
				startActivityForResult(i, 555);
			}
			
			 public void onFailure(Throwable e) {
	                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
	         }
			 
		}, tweetStatus);
	}
	
	
	public void goBacktoTimeline(View v){
		Intent i = new Intent(this, TimelineActivity.class);
//		i.putExtra(FOO_KEY, "bar");
//		i.putExtra(GOO_KEY, "baz");
//		i.putExtra(SETTINGS_KEY, settings);
		startActivityForResult(i, 444);
	}
	

}
