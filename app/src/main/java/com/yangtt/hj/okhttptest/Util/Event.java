package com.yangtt.hj.okhttptest.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.networkbench.agent.impl.NBSAppAgent;
import com.yangtt.hj.okhttptest.Data.MISC;

import java.io.IOException;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by hj on 2018/3/29.
 */

public class Event {
    public static String TAG="yttNBSAgent";
    public static int crash(){
            int[] count={0,1,2};
            return count[3];
    }
    public static void httpError(){
        String url="https://github.com/";
        asyncOkHttpPost(url);
    }
    public static void networkError(){
        String url="https://www.weather.com.cn";
        asyncOkHttpPost(url);
    }
    public static void asyncOkHttpPost(String url){
        MISC misc=new MISC();
        Gson gson=new Gson();
        String body=gson.toJson(misc);
        OkHttpUtil client=new OkHttpUtil();
        try {
            client.asyncPost(url,body);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void slowAction(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void asyncSlow(int timeInSeconds,Context context){
        MyThread thread=new MyThread(timeInSeconds,context);
        thread.start();
        try {
            Thread.sleep(4*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private static class MyThread extends Thread{
        private int timeInSeconds;
        private Context context;
        public MyThread(int timeInSeconds,Context context){
            this.timeInSeconds=timeInSeconds;
            this.context=context;
        }
        @Override
        public void run() {
            NBSAppAgent.beginTracer("MyThread");
            super.run();
            Log.d(TAG,"BlockTime:"+timeInSeconds);
            try {
                Thread.sleep(timeInSeconds*1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            call10086(context);
            NBSAppAgent.endTracer("MyThread");
        }
        void call10086(Context context){
            Uri uri = Uri.parse("tel:10086");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            context.startActivity(intent);
        }
    }

}
