package com.pdxx.ms.April.April_4_3;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/4/3.
 */

public interface JSZPService {
    @GET("version")
    Call<VersionData> getVersion();

    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> toLogin(@Field("userCode") String userCode, @Field("userPasswd") String userPasswd);

    @GET("version")
    Observable<VersionData> getRxJavaVersion();
}
