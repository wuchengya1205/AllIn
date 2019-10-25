package com.xiang.allin.utils;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :10:43
 * desc   :ohuo
 * version: 1.0
 */
public class OkhttpUtil {
    public static OkHttpClient okHttpClient=null;
    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkhttpUtil.class) {
                if (okHttpClient == null) {
                    File sdcache = new File(Environment.getExternalStorageDirectory(), "regist");
                    int cacheSize = 10 * 1024 * 1024;
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                            .build();
                }
            }

        }

        return okHttpClient;
    }

    public static void doGet(String url, Callback callback) {
        OkHttpClient okHttpClient = getInstance();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void doPost(String url, Map<String, String> params, Callback callback) {
        OkHttpClient okHttpClient = getInstance();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
