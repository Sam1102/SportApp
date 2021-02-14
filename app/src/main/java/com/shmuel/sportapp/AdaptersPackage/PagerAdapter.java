package com.shmuel.sportapp.AdaptersPackage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.shmuel.sportapp.PagesPackage.ChatFragment;
import com.shmuel.sportapp.PagesPackage.DataFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DataFragment dataFragment = new DataFragment();
                return dataFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
