package com.margo.postcard.activities;

import com.margo.postcard.R;
import com.margo.postcard.fragments.PhotoGalleryFragment;
import com.margo.postcard.fragments.SearchGalleryFragment;
import com.margo.postcard.network.FlickrClient;
import com.margo.postcard.network.FlickrClientApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserAccountActivity extends AppCompatActivity implements
        PhotoGalleryFragment.OnFragmentInteractionListener {

    @BindView(R.id.materialup_viewpager)
    ViewPager viewPager;

    @BindView(R.id.materialup_appbar)
    AppBarLayout appbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public TabLayout tabLayout;

    public static String TAG = "UserAA";

    private TextView mAppName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        ButterKnife.bind(this);

        tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        mAppName = (TextView) findViewById(R.id.appname);

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, UserAccountActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FlickrClient client = FlickrClientApp.getRestClient();
            client.clearAccessToken();
            onBackPressed();
        }
        return true;
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, UserAccountActivity.class));
    }

    @Override
    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }

    private static class TabsAdapter extends FragmentPagerAdapter {

        TabLayout tabLayout;

        private static final int TAB_COUNT = 2;
        private static String mPageTitle;

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            if (i == 0)
                return PhotoGalleryFragment.newInstance();
            else
                return SearchGalleryFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    mPageTitle = "Recent";
                    break;
                case 1:
                    mPageTitle = "Search";
                    break;
                default:
                    mPageTitle = "Error";
                    break;
            }
            return mPageTitle;
        }
    }
}
