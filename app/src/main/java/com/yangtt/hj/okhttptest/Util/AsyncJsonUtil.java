package com.yangtt.hj.okhttptest.Util;

import android.util.Log;

//import com.networkbench.agent.impl.//NBSAppAgent;
import com.yangtt.hj.okhttptest.Config;
import com.google.gson.Gson;
import com.yangtt.hj.okhttptest.Data.initData;
/**
 * Created by hj on 2017/12/12.
 */

public class AsyncJsonUtil {
    Config config =new Config();
    String TAG= config.getTAG();
    public void sendJson(){
        //NBSAppAgent.beginTracer("AsyncJsonUtil");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //NBSAppAgent.beginTracer("inThread");
                OkHttpUtil client=new OkHttpUtil();
                initData data=new initData();
                Gson gson=new Gson();
                String body=gson.toJson(data);
                Log.d(TAG,"AsyncJsonUtil send body:"+body);
                try {
                    Log.d(TAG,"OkHttp invoke time:"+System.currentTimeMillis());
                    String response = client.post("http://jsoneditoronline.org/", body);
                    /*String response = client.post("https://dc1alpha1.networkbench.com/initMobileApp?version=2.2.7", body);*/
                    Log.d(TAG,"AsyncJsonUtil response:"+response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //NBSAppAgent.endTracer("inThread");
            }
        }).start();
        try {
            Thread.sleep(2*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        //NBSAppAgent.endTracer("AsyncJsonUtil");
    }
}
