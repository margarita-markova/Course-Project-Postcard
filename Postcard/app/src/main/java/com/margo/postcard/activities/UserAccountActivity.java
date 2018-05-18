package com.margo.postcard.activities;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.margo.postcard.FakePageFragment;
import com.margo.postcard.R;
import com.margo.postcard.fragments.PhotoGalleryFragment;
import com.margo.postcard.models.GalleryItem;
import com.margo.postcard.models.User;
import com.margo.postcard.network.FlickrClient;
import com.margo.postcard.network.FlickrClientApp;
import com.margo.postcard.network.FlickrFetchr;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserAccountActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

        @BindView(R.id.materialup_tabs)
        TabLayout tabLayout;

        @BindView(R.id.materialup_viewpager)
        ViewPager viewPager;

       @BindView(R.id.materialup_appbar)
       AppBarLayout appbarLayout;

        @BindView(R.id.materialup_profile_image)
        ImageView mProfileImage;

        @BindView(R.id.toolbar)
        Toolbar toolbar;

        @BindView(R.id.title)
        TextView username;


        private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
        private boolean mIsAvatarShown = true;
        public static String TAG = "UserAA";

        private int mMaxScrollSize;

        private User mUser;

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_account);

            ButterKnife.bind(this);

            appbarLayout.addOnOffsetChangedListener(this);
            mMaxScrollSize = appbarLayout.getTotalScrollRange();


            viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(viewPager);

            setSupportActionBar(toolbar);

            new UserStartSettingsTask().execute();
            //username.setText(mUser.getUsername());
    }

    private class UserStartSettingsTask extends AsyncTask<Void,Void,User> {


        @Override
        protected User doInBackground(Void... params) {

            FlickrFetchr flickrFetchr = new FlickrFetchr();
            User user = flickrFetchr.testLogin();
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            username.setText(user.getUsername());
        }

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
        if(item.getItemId() == R.id.action_logout) {
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
        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (mMaxScrollSize == 0)
                mMaxScrollSize = appBarLayout.getTotalScrollRange();

            int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;



            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false;

                mProfileImage.animate()
                        .scaleY(0).scaleX(0)
                        .setDuration(200)
                        .start();
            }

            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true;

                mProfileImage.animate()
                        .scaleY(1).scaleX(1)
                        .start();
            }
        }

        private static class TabsAdapter extends FragmentPagerAdapter {
            private static final int TAB_COUNT = 3;
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
                if(i==1)
                    return PhotoGalleryFragment.newInstance();
                else
                    return FakePageFragment.newInstance();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position)
                {
                    case 1:
                        mPageTitle = "Recent";
                       // tabLayout.getTabAt(position).setIcon(R.drawable.your_icon);
                        break;
                    case 2:
                        mPageTitle = "Search";
                        break;
                    case 0:
                        mPageTitle = "Camera";
                        break;
                    default:
                        mPageTitle = "Error";
                        break;
                }
                return mPageTitle;
            }
        }
}
