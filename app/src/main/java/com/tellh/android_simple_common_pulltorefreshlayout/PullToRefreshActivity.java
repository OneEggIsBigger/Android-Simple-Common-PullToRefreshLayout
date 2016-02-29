package com.tellh.android_simple_common_pulltorefreshlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PullToRefreshActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ListViewFragment listviewFragment;
    private RecyclerViewFragment recyclerViewFragment;
    private SrollerViewFragment srollerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (viewPager.getCurrentItem()){
            case 0:
                listviewFragment.mPullToRefreshView.setRefreshing(true);
                break;
            case 1:
                recyclerViewFragment.mPullToRefreshView.setRefreshing(true);
                break;
            case 2:
                srollerViewFragment.mPullToRefreshView.setRefreshing(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    listviewFragment=new ListViewFragment();
                    return listviewFragment;
                case 1:
                    recyclerViewFragment = new RecyclerViewFragment();
                    return recyclerViewFragment;
                case 2:
                    srollerViewFragment = new SrollerViewFragment();
                    return srollerViewFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ListView";
                case 1:
                    return "RecyclerView";
                default:
                    return "ScrollView";
            }
        }
    }

}
