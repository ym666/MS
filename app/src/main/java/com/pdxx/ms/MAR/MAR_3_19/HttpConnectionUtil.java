package com.pdxx.ms.MAR.MAR_3_19;

import android.icu.util.Output;
import android.text.TextUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/19.
 */

public class HttpConnectionUtil {
    //配置http请求参数
    private static HttpURLConnection getHttpUrlConnection(String url, String method,boolean output) {
        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(output);

            urlConnection.setRequestMethod(method);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            return urlConnection;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    //把post参数加入输出流里
    private static void setOutputParams(OutputStream outputParams, List<NameValuePair> postParams) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (NameValuePair postParam : postParams) {
            if (!TextUtils.isEmpty(builder)) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(postParam.getName(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(postParam.getName(), "UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputParams, "UTF-8"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();

    }

    //设置post请求
    public static String useHttpConnectionPost(String url, Map<String, Object> map) {
        InputStream stream = null;
        try {
            HttpURLConnection httpUrlConnection = getHttpUrlConnection(url, "POST",true);
            List<NameValuePair> postParams = new ArrayList<>();
            for (String s : map.keySet()) {
                postParams.add(new BasicNameValuePair(s, (String) map.get(s)));
            }
            setOutputParams(httpUrlConnection.getOutputStream(), postParams);
            httpUrlConnection.connect();

            InputStream inputStream = httpUrlConnection.getInputStream();
            int responseCode = httpUrlConnection.getResponseCode();
            return converStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http not connect";
    }

    //设置get请求
    public static String useHttpConnectionGet(String url) {
        InputStream stream = null;
        try {
            HttpURLConnection httpUrlConnection = getHttpUrlConnection(url, "GET",false);
            httpUrlConnection.connect();

            InputStream inputStream = httpUrlConnection.getInputStream();
            int responseCode = httpUrlConnection.getResponseCode();
            return converStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http not connect";
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

}
