package com.yangtt.hj.okhttptest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.networkbench.agent.impl.NBSAppAgent;
import com.yangtt.hj.okhttptest.Util.OkHttpUtil;
import com.yangtt.hj.okhttptest.Util.URLConnectionUtil;

/**
 * Created by hj on 2017/12/13.
 */

public class yttApplication extends Application {
    Config config =new Config();
    String TAG= config.getTAG();
    public int count=0;
    private String tag="yttNBSAgent";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
/*        intTingyun(this.getApplicationContext());
        try {
            Thread.sleep(1*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        TestNetworkInApplication();*/
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }

    public String getTAG() {
        return TAG;
    }
    public void intTingyun(Context context){
        NBSAppAgent.setLicenseKey("03a6a7ff19a04962b23c601d51dafc80")
                .withLocationServiceEnabled(true)
                .enableLogging(true)
                .start(context);
        NBSAppAgent.setUserIdentifier("*#06#");
    }
    void TestNetworkInApplication(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //从网络获取数据
                Log.d(TAG,"invoke time:"+System.currentTimeMillis());
                /*final String response = URLConnectionUtil.post("http://m.tingyun.com","");*/
                OkHttpUtil util=new OkHttpUtil();
                util.post("http://m.tingyun.com","");
                Log.i(TAG,"request error");
            }
        }).start();
        try {
            Thread.sleep(2*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
