package com.codepath.apps.twitterClient.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.twitterClient.R;
import com.codepath.apps.twitterClient.models.Tweet;
import com.hp.app.twitterClient.EndlessScrollListener;
import com.hp.app.twitterClient.TweetsAdapter;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class TweetsListFragment extends Fragment {
	
	TweetsAdapter adapter;
	PullToRefreshListView lvTweets;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstaceState){
		return inf.inflate(R.layout.fragment_tweets_list, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstaceState){
		super.onActivityCreated(savedInstaceState);
		
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		Log.d("DEBUG", tweets.toString());
		
		adapter = new TweetsAdapter(getActivity(), tweets);
		adapter.addAll(tweets);
		lvTweets = (PullToRefreshListView) getActivity().findViewById(R.id.lv_tweets);
		lvTweets.setAdapter(adapter);
		lvTweets.onRefreshComplete();
		
		
		
		lvTweets.setOnRefreshListener(new OnRefreshListener() {
		
			@Override
			public void onRefresh() {
				makeAjaxCall(0); 
			}
		});
	
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
	        	Tweet tweet = (Tweet) lvTweets.getItemAtPosition(totalItemsCount - 1);
	        	if(tweet != null){
	        		makeAjaxCall(tweet.getId()); 
	        	}
	                // or customLoadMoreDataFromApi(totalItemsCount); 
	        }
	    });
			
		
	}
	
	public void makeAjaxCall(long max_id) {
		
	}
	
	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
	
}
