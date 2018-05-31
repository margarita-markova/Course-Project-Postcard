package com.margo.postcard;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

//import com.github.scribejava.core.builder.api.DefaultApi10a;
//import com.github.scribejava.apis.FlickrApi;

        //scribejava-core.src/main/java/com/github/scribejava/core/builder/api/DefaultApi10a.java;


import android.content.Context;
import android.util.Log;
/**
 * Created by Margo on 01.05.2018.
 */

public class Keys {
   public static final BaseApi REST_API_INSTANCE = FlickrApi.instance();
    public static final String REST_URL = "https://www.flickr.com/services/api/";
    public static final String REST_CONSUMER_KEY = "949f43acae31017cee4ebda25c42798a";
    public static final String REST_CONSUMER_SECRET = "9dcee93e1311be6d";
    public static final String REST_CALLBACK_URL = "appforflickr://callback";
    public static final String ERR_CODE_FLICKR_UNAVAILABLE = "105";


}
