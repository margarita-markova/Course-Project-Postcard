package com.margo.postcard.network;

import android.app.Application;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class FlickrClientApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlickrClientApp.context = this;
    }

    public static FlickrClient getRestClient() {
        return (FlickrClient) FlickrClient.getInstance(FlickrClient.class, FlickrClientApp.context);
    }
}
