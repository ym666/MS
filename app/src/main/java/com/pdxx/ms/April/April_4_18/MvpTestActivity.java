package com.pdxx.ms.April.April_4_18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pdxx.ms.R;

/**
 * Created by Administrator on 2018/4/18.
 */

public class MvpTestActivity extends AppCompatActivity implements View.OnClickListener, MvpTestView {

    private EditText et1;
    private EditText et2;
    private TextView tv;
    private MvpTestPresenter presenter;
    private TextView tv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        initview();
        presenter = new MvpTestPresenter(this);
    }

    private void initview() {
        et1 = ((EditText) findViewById(R.id.et1));
        et2 = ((EditText) findViewById(R.id.et2));
        findViewById(R.id.bt).setOnClickListener(this);
        tv2 = ((TextView) findViewById(R.id.tv2));
        tv = ((TextView) findViewById(R.id.tv));
    }

    @Override
    public void onClick(View v) {
        //点击登录
        presenter.login();
    }

    @Override
    public void loginSuccess() {
        tv.setText("登录成功");
    }

    @Override
    public void loginFail() {
        tv.setText("登录失败");
    }

    @Override
    public void getRandom(int i) {

        tv2.setText(i + "");
    }
}
