package com.pdxx.ms.April.April_4_11;

import android.content.Intent;
import android.view.View;

/**
 * Created by Administrator on 2018/4/11.
 */

public class LanchModeTest3Activity extends LanchModeBaseActivity {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LanchModeTest1Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
