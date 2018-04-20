package com.pdxx.ms.April.April_4_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pdxx.ms.R;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit2TestActivity extends AppCompatActivity implements View.OnClickListener {

    private JSZPService jszpService;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2_test);
        jszpService = Retrofit2Test1.buildRetrofit();
        findViewById(R.id.bt_get).setOnClickListener(this);
        findViewById(R.id.bt_post).setOnClickListener(this);
        findViewById(R.id.bt_rxjava_get).setOnClickListener(this);
        textView = ((TextView) findViewById(R.id.tv));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get:
                jszpService.getVersion().enqueue(new Callback<VersionData>() {
                    @Override
                    public void onResponse(Call<VersionData> call, Response<VersionData> response) {
                        textView.setText(response.body().data.toString());
                    }

                    @Override
                    public void onFailure(Call<VersionData> call, Throwable t) {
                        textView.setText(t.toString());
                    }
                });
                break;
            case R.id.bt_post:
                jszpService.toLogin("123", "njdsaa").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            textView.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        textView.setText(t.toString());
                    }
                });
                break;
            case R.id.bt_rxjava_get:
                jszpService.getRxJavaVersion()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<VersionData>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(VersionData versionData) {
                                textView.setText(versionData.data.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
}
