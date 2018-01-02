package com.yangtt.hj.okhttptest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by hj on 2017/12/20.
 */

public class Activity1 extends AppCompatActivity {
    Config config =new Config();
    String TAG= config.getTAG();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentroot);
        Log.d(TAG,"Activity1 onCreated...");
/*        ImageView imageView=(ImageView)findViewById(R.id.test_image);
        Bitmap pic= BitmapFactory.decodeResource(getResources(),R.drawable.test);
        imageView.setImageBitmap(pic);*/
        config.waitSeconds(10);
        yttApplication app=(yttApplication)this.getApplicationContext();
        int count=app.getCount();
        Log.d(TAG,""+count);
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        Log.d(TAG,"return MainActivity onCall...");
        Activity1.this.startActivity(intent);
    }
}
