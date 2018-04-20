package com.pdxx.ms.April.April_4_4;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.pdxx.ms.R;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/4.
 */

public class PhotoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_test);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        findViewById(R.id.bt_takephoto).setOnClickListener(this);
        findViewById(R.id.bt_fromcontent).setOnClickListener(this);
        findViewById(R.id.bt_crash).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_takephoto:
                //拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                File file = new File(Environment.getExternalStorageDirectory() + "/test");
                if (!file.exists() || !file.isDirectory()) {
                    file.mkdirs();
                }
                file = new File(file, "test.jpg");
                Uri url = FileProvider.getUriForFile(this, "hehe", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, url);
                startActivityForResult(intent, 1);

                break;
            case R.id.bt_fromcontent:
                //从相册选取
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 2);
                break;
            case R.id.bt_crash:
                CrashReport.initCrashReport(this);
                CrashReport.testJavaCrash();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/test/test.jpg");
            iv.setImageBitmap(bitmap);
            tv.setText("bitmap宽度" + bitmap.getWidth() + "\n" + "bitmap高度" + bitmap.getHeight()
                    + "\nbitmap内存" + bitmap.getByteCount()
            );
        }

        if (requestCode == 2) {
            if (data != null) {
                Uri uri = data.getData();
                Glide.with(this).load(uri).into(iv);
            }

//
//            ContentResolver resolver = getContentResolver();
//            Bitmap bitmap = null;
//            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
//                iv.setImageBitmap(bitmap);
//                tv.setText("bitmap宽度" + bitmap.getWidth() + "\n" + "bitmap高度" + bitmap.getHeight()
//                        + "\nbitmap内存" + bitmap.getByteCount()
//                );
//                bitmap.recycle();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }
}
