package com.hp.app.twitterClient;

import java.net.URLEncoder;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "nhHPsagq4tiMmFDAJ4X6bVIue";       // Change this
    public static final String REST_CONSUMER_SECRET = "BiAJTwCM6md9F1Jf8fRjXFOoTHTN2SAhqH0ex4UVDvNBajmGeD"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://myTwitterAppCallback"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    
    public void getHomeTimeLine(AsyncHttpResponseHandler handler, long max_id) {
        String url = "statuses/home_timeline.json?count=25";
        
        if(max_id > 0){
        	url = url + ("&max_id=" + max_id);
        }
        
    	String apiUrl = getApiUrl(url);
        client.get(apiUrl, null, handler);
    }
    
    public void getMentinosTimeLine(AsyncHttpResponseHandler handler, long max_id) {
        String url = "statuses/mentions_timeline.json";
        
        if(max_id > 0){
        	url = url + ("&max_id=" + max_id);
        }
        
    	String apiUrl = getApiUrl(url);
        client.get(apiUrl, null, handler);
    }
    
    
    public void getUserTimeLine(AsyncHttpResponseHandler handler, long max_id, String screenName) {
    	String url = "";
    	if(screenName != null && !screenName.isEmpty()){
    		url = "statuses/user_timeline.json?screen_name="+screenName;	
    	}else{
    		url = "statuses/user_timeline.json";
    	}
        
        if(max_id > 0){
        	url = url + ("&max_id=" + max_id);
        }
        
    	String apiUrl = getApiUrl(url);
        client.get(apiUrl, null, handler);
    }
    
    
    public void getMyInfo(AsyncHttpResponseHandler handler) {
        String url = "account/verify_credentials.json";
    	String apiUrl = getApiUrl(url);
        client.get(apiUrl, null, handler);
    }
    
    
    public void getProfileInfo(AsyncHttpResponseHandler handler, String screenName) {
        String url = "users/show.json?screen_name="+screenName;
    	String apiUrl = getApiUrl(url);
        client.get(apiUrl, null, handler);
    }
    
    
    public void postNewTweet(AsyncHttpResponseHandler handler, String tweetText) {
        String url = "statuses/update.json";
        
        if(tweetText.length() > 0){
        	url = url + ("?status=" + URLEncoder.encode(tweetText));
        }
            
    	String apiUrl = getApiUrl(url);
        client.post(apiUrl, null, handler);
    }
    
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}