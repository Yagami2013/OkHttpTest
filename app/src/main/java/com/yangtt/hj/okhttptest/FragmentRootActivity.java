package com.yangtt.hj.okhttptest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.networkbench.agent.impl.NBSAppAgent;
import com.yangtt.hj.okhttptest.R;

/**
 * Created by hj on 2017/12/22.
 */

public class FragmentRootActivity extends Activity {
    Config config=new Config();
    String TAG=config.getTAG();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentroot);
        Log.d(TAG,"FragmentRootActivity onCreate...");
    }
    void TestInitInSecondActivity(){
        NBSAppAgent.setLicenseKey("03a6a7ff19a04962b23c601d51dafc80")
                .withLocationServiceEnabled(true).enableLogging(true)
                .start(this.getApplicationContext());
        NBSAppAgent.setUserIdentifier("*#06#");
    }
}
