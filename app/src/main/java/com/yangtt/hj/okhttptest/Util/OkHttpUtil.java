package com.yangtt.hj.okhttptest.Util;

/**
 * Created by hj on 2017/12/11.
 */

import android.util.Log;

import com.yangtt.hj.okhttptest.Config;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    Config config =new Config();
    String TAG= config.getTAG();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public void asyncPost(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Log.d(TAG,"OkHttp Entry Time:"+System.currentTimeMillis());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    Log.d(TAG,"request Failure");
                    Log.d(TAG,"ThreadName:"+Thread.currentThread().getName()+" Thread ID:"+Thread.currentThread().getId());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    //Log.d(TAG,url+" res:"+ response.body().string());
                    Log.d(TAG,"ThreadName:"+Thread.currentThread().getName()+" Thread ID:"+Thread.currentThread().getId());
            }
        });
    }
    public String post(String url, String json){
        RequestBody body=RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Log.d(TAG,"OkHttp Entry Time:"+System.currentTimeMillis());
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public void head(){

    }

}
