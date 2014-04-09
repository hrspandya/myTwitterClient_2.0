package com.codepath.apps.twitterClient.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.format.DateUtils;

public class User extends BaseModel{
		private String name;
		private long uid;
		private String screenName;
		private String profileBgImageUrl;
		private int numTweets;
		private int followersCount;
		private int friendsCount;
		private String timeStamp;
		private String tagLine;

	    public String getTagLine() {
			return tagLine;
		}

		public String getName() {
	        return name;
	    }

	    public long getId() {
	        return uid;
	    }

	    public String getScreenName() {
	        return screenName;
	    }

	    public String getProfileBackgroundImageUrl() {
	        return profileBgImageUrl;
	    }

	    public int getNumTweets() {
	        return numTweets;
	    }

	    public int getFollowersCount() {
	        return followersCount;
	    }

	    public int getFriendsCount() {
	        return friendsCount;
	    }
	    
	    public String getTimeStamp() {
	        return timeStamp;
	    }

	    public static User fromJson(JSONObject json) {
	        User u = new User();
	        try {
	        	u.name = json.getString("name");
	        	u.uid = json.getLong("id");
	        	u.screenName = json.getString("screen_name");
	        	u.profileBgImageUrl = json.getString("profile_image_url");
	        	u.numTweets = json.getInt("statuses_count");
	        	u.followersCount = json.getInt("followers_count");
	        	u.friendsCount = json.getInt("friends_count");
	        	u.timeStamp = json.getString("created_at");
	        	u.tagLine = json.getString("description");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return u;
	    }


	}