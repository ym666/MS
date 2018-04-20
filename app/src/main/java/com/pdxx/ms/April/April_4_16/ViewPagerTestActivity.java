package com.pdxx.ms.April.April_4_16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pdxx.ms.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ViewPagerTestActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagertest);
        vp = ((ViewPager) findViewById(R.id.vp));
        fragments = new ArrayList<>();
        fragments.add(new TestFragment1());
        fragments.add(new TestFragment2());
        fragments.add(new TestFragment3());
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(new Myadapter(getSupportFragmentManager()));

    }

    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
