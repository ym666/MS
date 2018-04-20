package com.pdxx.ms.April.April_4_8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pdxx.ms.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class FileTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filetest);
        tv = findViewById(R.id.tv);
        findViewById(R.id.bt_file).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_file:
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();

            String filePathByUri = FileUtil.getFilePathByUri(this, uri);
            tv.setText("uri路径为   " + uri.getPath() + "\nuri为   "
                    + uri.toString() + "\nuri转绝对路径   " + filePathByUri);
        }
    }
}
