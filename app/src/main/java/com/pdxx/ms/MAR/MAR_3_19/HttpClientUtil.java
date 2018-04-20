package com.pdxx.ms.MAR.MAR_3_19;

import android.support.v7.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类 在android 6.0删除了这个类库，如果想使用必须添加
 * android {
 * useLibrary 'org.apache.http.legacy'
 * }依赖
 */

public class HttpClientUtil {

    //创建httpClient
    private static HttpClient createHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 20000);
        HttpConnectionParams.setSoTimeout(params, 20000);
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpClient client = new DefaultHttpClient(params);
        return client;
    }

    //get请求
    public static String setGetRequest(String url) {
        HttpGet get = new HttpGet(url);
        get.setHeader("Connection", "Keep-Alive");
        HttpClient client = createHttpClient();
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (null != entity) {
                InputStream inputStream = entity.getContent();
                return converStreamToString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "no http connect";
    }

    private static String converStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    public static String setPostRequest(String url, Map<String, Object> map) {
        if (null == map) {
            return "map should not be null";
        }
        HttpPost post = new HttpPost(url);
        HttpClient httpClient = createHttpClient();
        try {
            List<NameValuePair> postParams = new ArrayList<>();
            for (String s : map.keySet()) {
                postParams.add(new BasicNameValuePair(s, (String) map.get(s)));
            }
            post.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (null != entity) {
                InputStream inputStream = entity.getContent();
                return converStreamToString(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "no http connect";
    }
}
