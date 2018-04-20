package com.pdxx.ms.MAR.MAR_3_20;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pdxx.ms.MAR.MAR_3_19.HttpClientUtil;
import com.pdxx.ms.MAR.MAR_3_19.HttpConnectionUtil;
import com.pdxx.ms.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/20.
 */

public class OkHttpTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttptest);
        findViewById(R.id.bt).setOnClickListener(this);
        findViewById(R.id.bt_post).setOnClickListener(this);
        findViewById(R.id.bt_get).setOnClickListener(this);
        findViewById(R.id.bt_post_httpconnection).setOnClickListener(this);
        textView = ((TextView) findViewById(R.id.text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                Request request = new Request.Builder().url("http://jsapp.ezhupei.com/pdapp/res/jswjw/version").build();
                Call getCall = client.newCall(request);
                getCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", response.body().string());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                });

                break;
            case R.id.bt_post:
                RequestBody requestBody = new FormBody.Builder()
                        .add("userCode","123")
                        .add("passWord","123456")
                        .build();
                Request request2 = new Request.Builder().url("http://jsapp.ezhupei.com/pdapp/res/jswjw/login").post(requestBody).build();
                Call postCall = client.newCall(request2);
                postCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", response.body().string());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                });

                break;
            case R.id.bt_get:
                break;
            case R.id.bt_post_httpconnection:
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
