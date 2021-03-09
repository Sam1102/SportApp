package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.shmuel.sportapp.AdaptersPackage.PagerAdapter;
import com.shmuel.sportapp.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        showUI();
    }

    private void initUI() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);
    }

    private void showUI() {
        tabLayout.addTab(tabLayout.newTab().setText("Your Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Chat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
