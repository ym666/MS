package com.pdxx.ms.MAR.MAR_3_19;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pdxx.ms.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/19.
 */

public class HttpTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httptest);
        findViewById(R.id.bt).setOnClickListener(this);
        findViewById(R.id.bt_post).setOnClickListener(this);
        findViewById(R.id.bt_get).setOnClickListener(this);
        findViewById(R.id.bt_post_httpconnection).setOnClickListener(this);
        textView = ((TextView) findViewById(R.id.text));
    }

    String response;

    @Override
    public void onClick(View v) {
        final Map<String, Object> map = new HashMap<>();
        map.put("userCode", "123456");
        map.put("password", "123456");
        switch (v.getId()) {
            case R.id.bt:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        response = HttpClientUtil.setGetRequest("http://jsapp.ezhupei.com/pdapp/res/jswjw/version");
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", response);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();

                break;
            case R.id.bt_post:

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                String response = HttpClientUtil.setPostRequest("http://jsapp.ezhupei.com/pdapp/res/jswjw/login", map);
                                Message message = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putString("data", response);
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                        }
                ).start();
                break;
            case R.id.bt_get:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        response = HttpConnectionUtil.useHttpConnectionGet("http://jsapp.ezhupei.com/pdapp/res/jswjw/version");
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", response);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.bt_post_httpconnection:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        response = HttpConnectionUtil.useHttpConnectionPost("http://jsapp.ezhupei.com/pdapp/res/jswjw/login", map);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", response);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            String response = data.getString("data");
            textView.setText(response);
        }
    };
}

