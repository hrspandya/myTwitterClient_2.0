package com.codepath.apps.twitterClient.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.twitterClient.models.Tweet;
import com.hp.app.twitterClient.TwitterClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class UserTimelineFragment extends TweetsListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		long max_id = 0;
		
		makeAjaxCall(max_id, null);	
	}
	
	
	public void makeAjaxCall(long max_id, String screenName) {
		
			if(screenName != null){
				
			}
			
			TwitterClientApp.getRestClient().getUserTimeLine(new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONArray jsonTweets) {
					
					getAdapter().clear();
					ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
					getAdapter().addAll(tweets);
					Log.d("DEBUG", tweets.toString());				
				}
				
				 public void onFailure(Throwable e) {
		                Log.d("DEBUG", "Fetch mention timeline error: " + e.toString());
		         }
				 
			}, max_id, screenName);
		
	}
}
