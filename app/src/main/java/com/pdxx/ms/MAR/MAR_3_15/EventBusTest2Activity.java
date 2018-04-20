package com.pdxx.ms.MAR.MAR_3_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pdxx.ms.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusTest2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test2);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.message = "我是第二个activity发布的事件";
                EventBus.getDefault().post(messageEvent);
            }
        });
    }
}
