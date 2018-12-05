package com.margo.postcard.activities;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.oauth.OAuthLoginActivity;
import com.margo.postcard.R;
import com.margo.postcard.network.FlickrClient;

import java.io.File;

import butterknife.BindView;

import static com.margo.postcard.Keys.REST_CONSUMER_KEY;
import static com.margo.postcard.Keys.REST_CONSUMER_SECRET;

public class LoginActivity extends OAuthLoginActivity<FlickrClient> implements View.OnClickListener {
    private static final String TAG = "log";

    @BindView(R.id.textView2)
    TextView appName;

    @BindView(R.id.button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onLoginSuccess() {
        Intent changeActivity = new Intent(this, UserAccountActivity.class);
        startActivity(changeActivity);
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onClick(View v) {
        if (!getClient().isAuthenticated()) {
            getClient().connect();
        }
        //требуется дважды нажимать на кнопку? Сделай стартовой активностью другую, которая вызовет
        //startForResultActivity(LoginActivity), для этого измени манифест
        else {
            onLoginSuccess();
        }
    }

}
