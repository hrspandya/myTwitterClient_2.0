package com.hp.app.twitterClient;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterClient.R;
import com.codepath.apps.twitterClient.fragments.UserTimelineFragment;
import com.codepath.apps.twitterClient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		loadProfileInfo();
	}

	private void loadProfileInfo() {
		
		String screenName = (String) getIntent().getSerializableExtra("userId");
		
		if(screenName != null && !screenName.isEmpty()){
			UserTimelineFragment userFragment = (UserTimelineFragment) 
					getSupportFragmentManager().findFragmentById(R.id.fragmentUserTimeline);
			userFragment.makeAjaxCall(0, screenName);
		}
		
		if(screenName != null && !screenName.isEmpty()){
			TwitterClientApp.getRestClient().getProfileInfo(new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject json){
					 User u = User.fromJson(json);
					 getActionBar().setTitle("@" + u.getScreenName());
					 populateProfileHeader(u);
				}
				
				public void onFailure(Throwable e) {
	                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
				}
				
			}, screenName);
		}else{
			
			TwitterClientApp.getRestClient().getMyInfo(new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject json){
					User u = User.fromJson(json);
					getActionBar().setTitle("@" + u.getScreenName());
					populateProfileHeader(u);
				}
				
			});
		}
		
		
		
	}

	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		
		tvName.setText(u.getName());
		tvTagline.setText(u.getTagLine());
		tvFollowers.setText(String.valueOf(u.getFollowersCount()) + " Followers");
		tvFollowing.setText(String.valueOf(u.getFriendsCount()) + " Following");
		ImageLoader.getInstance().displayImage(u.getProfileBackgroundImageUrl(), ivProfileImage);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
