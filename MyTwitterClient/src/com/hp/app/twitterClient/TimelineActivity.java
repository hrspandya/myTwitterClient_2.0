package com.hp.app.twitterClient;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.twitterClient.R;
import com.codepath.apps.twitterClient.fragments.HomeTImelineFragment;
import com.codepath.apps.twitterClient.fragments.MentionsFragment;
import com.codepath.apps.twitterClient.models.Tweet;

import eu.erikw.PullToRefreshListView;

public class TimelineActivity extends FragmentActivity implements TabListener {
	
	private PullToRefreshListView lvTweets;
	public static final int SETTINGS_REQUEST = 123;
	private TweetsAdapter adapter;
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setUpNavigationTab();
	}


	private void setUpNavigationTab() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTImelineFragment")
				.setIcon(R.drawable.ic_home)
				.setTabListener(this);
		
		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsTimelineFragment")
				.setIcon(R.drawable.ic_mentions)
				.setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		
		actionBar.selectTab(tabHome);
	}


	public void onComposePress(MenuItem miCompose){
		Intent i = new Intent(this, ComposeActivity.class);
//		i.putExtra(FOO_KEY, "bar");
//		i.putExtra(GOO_KEY, "baz");
//		i.putExtra(SETTINGS_KEY, settings);
		startActivityForResult(i, SETTINGS_REQUEST);
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		if(tab.getTag() == "HomeTImelineFragment"){
			//set the fragment to home
			fts.replace(R.id.frame_container, new HomeTImelineFragment());
		}else{
			//set the fragment to mentions
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		
		fts.commit();
	}

	public void onProfileView(MenuItem mi){
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
