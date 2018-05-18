package com.margo.postcard.network;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.util.Log;

import static com.margo.postcard.Keys.REST_API_INSTANCE;
import static com.margo.postcard.Keys.REST_CALLBACK_URL;
import static com.margo.postcard.Keys.REST_CONSUMER_KEY;
import static com.margo.postcard.Keys.REST_CONSUMER_SECRET;
import static com.margo.postcard.Keys.REST_URL;

public class FlickrClient extends OAuthBaseClient {

    public FlickrClient(Context context) {
        super(context, REST_API_INSTANCE, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET,
                REST_CALLBACK_URL);
        setBaseUrl("https://api.flickr.com/services/rest");
    }

    public void getLogin(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?format=json&nojsoncallback=1&method=flickr.test.login");
        Log.d("DEBUG", "Sending API call to " + apiUrl);
        //client.get(apiUrl, null, handler);
       // //client.
      //  Log.i("hmm", "jjjj");
    }
}