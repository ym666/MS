package com.pdxx.ms.April.April_4_17;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

import com.pdxx.ms.MAR.MAR_3_19.HttpClientUtil;
import com.pdxx.ms.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar pb;
    private static RemoteViews remoteViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        pb = ((ProgressBar) findViewById(R.id.pb));
        findViewById(R.id.bt_down).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_down:
                MyAsyncTask myAsyncTask = new MyAsyncTask(new SoftReference<AsyncTaskActivity>(this));
                myAsyncTask.execute("http://app.njpdxx.com/ios/res_jswjw/res_jswjw_android_1.4.18_release.apk");
                break;
        }
    }

    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {


        final SoftReference<AsyncTaskActivity> activitysf;
        private long contentLength;
        private NotificationManager manager;
        private Notification.Builder builder;
        private Notification build;


        private MyAsyncTask(SoftReference<AsyncTaskActivity> activitysf) {
            this.activitysf = activitysf;
        }

        @Override
        protected void onPreExecute() {
            AsyncTaskActivity asyncTaskActivity = asyncTaskActivity = activitysf.get();
            super.onPreExecute();
            //弹出通知
            if (Build.VERSION_CODES.N <= Build.VERSION.SDK_INT) {
                builder = new Notification.Builder(asyncTaskActivity);
                remoteViews = new RemoteViews(asyncTaskActivity.getPackageName(), R.layout.notification);
                remoteViews.setProgressBar(R.id.pb, 100, 0, false);
                builder.setCustomBigContentView(remoteViews);
                builder.setSmallIcon(R.mipmap.icon_jszy);
                manager = (NotificationManager) asyncTaskActivity.getSystemService(NOTIFICATION_SERVICE);
                build = builder.build();
            }

        }

        //子线程下载文件
        @Override
        protected String doInBackground(String... strings) {
            //防止内存泄漏，静态内部类不会持有外部类的引用
            AsyncTaskActivity asyncTaskActivity = asyncTaskActivity = activitysf.get();
            ProgressBar pb = asyncTaskActivity.findViewById(R.id.pb);
            Log.e("asynctask", strings[0] + "");
            for (String string : strings) {
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params, 20000);
                HttpConnectionParams.setSoTimeout(params, 20000);
                HttpConnectionParams.setTcpNoDelay(params, true);
                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
                HttpProtocolParams.setUseExpectContinue(params, true);
                HttpClient client = new DefaultHttpClient(params);

                HttpGet get = new HttpGet(string);
                get.setHeader("Connection", "Keep-Alive");
                try {
                    HttpResponse response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int code = response.getStatusLine().getStatusCode();
                    if (null != entity) {
                        contentLength = entity.getContentLength();
                        pb.setMax((int) contentLength);
                        InputStream inputStream = entity.getContent();
                        File file = new File(Environment.getExternalStorageDirectory() + "/test/", "test.apk");
                        //输出流
                        FileOutputStream fos = new FileOutputStream(file);
                        //输入流
                        BufferedInputStream bis = new BufferedInputStream(inputStream);
                        byte[] buffer = new byte[1024];
                        int len;
                        int total = 0;
                        while ((len = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            total += len;
                            publishProgress(total);

                        }
                        fos.close();
                        bis.close();
                        inputStream.close();
                        return file.getAbsolutePath();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            AsyncTaskActivity asyncTaskActivity = asyncTaskActivity = activitysf.get();
            ProgressBar pb = asyncTaskActivity.findViewById(R.id.pb);
            super.onProgressUpdate(values);
//            Log.e("asynctask", values[0] + "");
            pb.setProgress(values[0]);
            //更改通知栏的progressbar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                remoteViews.setProgressBar(R.id.pb, (int) contentLength, values[0], false);
                manager.notify(0x3, build);
            }
        }

        //主线程安装文件
        @Override
        protected void onPostExecute(String s) {
            AsyncTaskActivity asyncTaskActivity = asyncTaskActivity = activitysf.get();
            ProgressBar pb = asyncTaskActivity.findViewById(R.id.pb);
            super.onPostExecute(s);
            if (TextUtils.isEmpty(s)) {
                return;
            }
            File file = new File(s);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // "com.example.cc.FileProvider"即是在清单文件中配置的authorities,可以随意定义，但要保持唯一
                uri = FileProvider.getUriForFile(asyncTaskActivity, "hehe", file);
                // 给目标应用一个临时授权
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(file);
            }
//            List<ResolveInfo> resInfoList = AsyncTaskActivity.this.getPackageManager()
//                    .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//            for (ResolveInfo resolveInfo : resInfoList) {
//                String packageName = resolveInfo.activityInfo.packageName;
//                AsyncTaskActivity.this.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            asyncTaskActivity.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
