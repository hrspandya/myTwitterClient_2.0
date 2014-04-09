package com.codepath.apps.twitterClient.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet extends BaseModel{

		private String body;
		private long uid;
		private boolean favorited;
		private boolean retweeted;
	    private User user;
	    private String created_at;

	    public User getUser() {
	        return user;
	    }

	    public String getCreatedAt() {
	        return created_at;
	    }
	    
	    public String getBody() {
	        return body;
	    }

	    public long getId() {
	        return uid;
	    }

	    public boolean isFavorited() {
	        return favorited;
	    }

	    public boolean isRetweeted() {
	        return retweeted;
	    }

	    public static Tweet fromJson(JSONObject jsonObject) {
	        Tweet tweet = new Tweet();
	        try {
	        	tweet.body = jsonObject.getString("text");
	        	tweet.uid = jsonObject.getLong("id");
	        	tweet.favorited = jsonObject.getBoolean("favorited");
	        	tweet.retweeted = jsonObject.getBoolean("retweeted");
	        	tweet.created_at = jsonObject.getString("created_at");
	            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
	        } catch (JSONException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return tweet;
	    }

	    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
	        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

	        for (int i=0; i < jsonArray.length(); i++) {
	            JSONObject tweetJson = null;
	            try {
	                tweetJson = jsonArray.getJSONObject(i);
	            } catch (Exception e) {
	                e.printStackTrace();
	                continue;
	            }

	            Tweet tweet = Tweet.fromJson(tweetJson);
	            if (tweet != null) {
	                tweets.add(tweet);
	            }
	        }

	        return tweets;
	    }
	
}
