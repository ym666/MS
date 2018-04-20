package com.pdxx.ms.April.April_4_11;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/11.
 */

public class LanchModeTest2Activity extends LanchModeBaseActivity {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LanchModeTest3Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("test");
            Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("test", "005");
    }

}
