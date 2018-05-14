package com.yangtt.hj.okhttptest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.networkbench.agent.impl.NBSAppAgent;
import com.yangtt.hj.okhttptest.R;

/**
 * Created by hj on 2017/12/22.
 */

public class LoginActivity extends Activity {
    private static int countButtonClickTime=0;
    Config config=new Config();
    String TAG=config.getTAG();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d(TAG,"LoginActivity onCreate...");

        Button button_ok=(Button)findViewById(R.id.bt_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countButtonClickTime++;
                NBSAppAgent.setUserCrashMessage("click ",""+countButtonClickTime);
                Log.d(TAG,"Login button clicked"+countButtonClickTime);
            }
        });
        Button button_test=(Button)findViewById(R.id.btnTest);
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d(TAG,"LoginActivity onCreate2...");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG,"LoginActivity onSaveInstanceState...");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"LoginActivity onRestoreInstanceState...");
    }
}
