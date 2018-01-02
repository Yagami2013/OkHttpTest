package com.yangtt.hj.okhttptest;

/**
 * Created by hj on 2017/12/13.
 */

public class Config {
    private String TAG="yttNBSAgent";
    public String getTAG(){
        return TAG;
    }
    public void waitSeconds(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
