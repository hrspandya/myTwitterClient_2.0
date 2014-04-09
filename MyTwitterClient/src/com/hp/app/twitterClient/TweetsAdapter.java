package com.hp.app.twitterClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterClient.R;
import com.codepath.apps.twitterClient.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet>{

	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.tweet_item, null);
		}
		
		Tweet tweet = getItem(position);
		
		SimpleDateFormat f = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
		Date d;
		CharSequence str = "";
		//Wed Mar 07 01:27:09 +0000 2007    
		try {
			d = f.parse(tweet.getCreatedAt());
			long milliseconds = d.getTime();
		    str = DateUtils.getRelativeDateTimeString(
					getContext(),
					milliseconds, // The time to display
					
					SimpleDateFormat.MINUTE_FIELD, // The resolution. This will display only 
					// minutes (no "3 seconds ago") 
					
					
					SimpleDateFormat.WEEK_OF_MONTH_FIELD, // The maximum resolution at which the time will switch 
					// to default date instead of spans. This will not 
					// display "3 weeks ago" but a full date instead
					
					0); // Eventual flags
		    
		    
		    str = DateUtils.getRelativeTimeSpanString(
		    	     d.getTime(), 
		    	     System.currentTimeMillis(), 
		    	     DateUtils.SECOND_IN_MILLIS,
		    	     0);
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		str = str.toString().replace("minutes", "m");
		str = str.toString().replace("hours", "h");
		str = str.toString().replace("hour", "h");
		str = str.toString().replace("ago", "");
		str = str.toString().replace(" ", "");
		
		ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileBackgroundImageUrl(), imageView);
		
		final String screenName = tweet.getUser().getScreenName();
				
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), ProfileActivity.class);
				
				i.putExtra("userId", screenName);
				getContext().startActivity(i);
			}
		});

		
		
		TextView nameView = (TextView) view.findViewById(R.id.tvName);
		String formattedName = "<br><b>" + tweet.getUser().getName() + "</b>" + "<small> <font color='#777777'>@" +
		tweet.getUser().getScreenName() + "</font></small><small align='right'>      "+str+"</small>";
		nameView.setText(Html.fromHtml(formattedName));
		
		TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
		
		
		bodyView.setText(Html.fromHtml(tweet.getBody()));
		
		return view;
	}
	
}
