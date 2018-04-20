package com.pdxx.ms.April.April_4_16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pdxx.ms.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ChangeFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Fragment> fragments;
    FragmentManager ft = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changefragment);
        findViewById(R.id.bt_fragment1).setOnClickListener(this);
        findViewById(R.id.bt_fragment2).setOnClickListener(this);
        findViewById(R.id.bt_fragment3).setOnClickListener(this);
        fragments = new ArrayList<>();
        fragments.add(new TestFragment1());
        fragments.add(new TestFragment2());
        fragments.add(new TestFragment3());
    }

    private void changeFragment(int i) {
        FragmentTransaction fragmentTransaction = ft.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        Fragment fragment = fragments.get(i);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fl, fragment);
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fragment1:
                changeFragment(0);
                break;
            case R.id.bt_fragment2:
                changeFragment(1);
                break;
            case R.id.bt_fragment3:
                changeFragment(2);
                break;
        }
    }
}
