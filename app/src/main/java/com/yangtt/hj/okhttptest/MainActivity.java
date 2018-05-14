package com.yangtt.hj.okhttptest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;

import com.google.gson.Gson;
import com.yangtt.hj.okhttptest.Data.Book;
import com.yangtt.hj.okhttptest.Data.MISC;
import com.yangtt.hj.okhttptest.Util.AsyncJsonUtil;
import com.yangtt.hj.okhttptest.Util.Event;
import com.yangtt.hj.okhttptest.Util.MQTTSend;
import com.yangtt.hj.okhttptest.Util.MqttRecv;
import com.yangtt.hj.okhttptest.Util.OkHttpUtil;
import com.networkbench.agent.impl.NBSAppAgent;
import com.yangtt.hj.okhttptest.Util.SQLiteUtil;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Config config =new Config();
    String TAG= config.getTAG();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"MainActivity onCreate...");
        setContentView(R.layout.activity_main);
        intTingyun();
		Log.d(TAG,"Test jenkins****");
        //TestCrashAfterTingyun(getApplicationContext());
        /*TestCrashBeforeTingyun(getApplicationContext());*/
        /*TestBitmapInCustomTrace();*/

        /*setContentView(R.layout.activity_main);*/


       /* TestOkHttpAsyncPost();*/
        buttonClickEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"MainActivity onResume...");
        /*MobclickAgent.onResume(this);*/
        /*Login();*/
        /*TestAlertDilog();*/
        /*TestOpenFragment();*/
        /*TestSQL();*/
        /*TestCustomTrace();*/
        TestOkHttpAsyncPost();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*MobclickAgent.onPause(this);*/
    }

    void intTingyun(){
        Log.d(TAG,"initTingyun...");
        //out key a32e68ea65c1471bab16804365189d56
        //out-text_chengmy 1374145e792f408495390f14a7ea1ce4
        //inner key:ytt-new:03a6a7ff19a04962b23c601d51dafc80
        NBSAppAgent.setLicenseKey("858af234b934419a302a1f410656")
                .withLocationServiceEnabled(true).enableLogging(true)
                .start(this.getApplicationContext());
        NBSAppAgent.setUserIdentifier("*#06#");
    }
    void initUmeng(){

    }

/*    void TestBitmapInCustomTrace(){
        Log.d(TAG,"TestBitmapInCustomTrace....");
        NBSAppAgent.beginTracer("123");
        ImageView imageView=(ImageView)findViewById(R.id.test_image);
        Bitmap pic= BitmapFactory.decodeResource(getResources(),R.drawable.test);
        imageView.setImageBitmap(pic);
        NBSAppAgent.endTracer("123");
    }*/

    void TestAsyncJson(){
        AsyncJsonUtil json=new AsyncJsonUtil();
        json.sendJson();
    }

    void TestSyncJson(){
        MISC data=new MISC();
        Gson gson=new Gson();
        final String body=gson.toJson(data);
/*        Log.d(TAG,"send body:"+body);
        NBSAppAgent.beginTracer("testerhome");
        TestOkHttpPost("https://testerhome.com/", body);
        NBSAppAgent.endTracer("testerhome");*/
    }
    void TestOkHttpAsyncPost(){
        MISC misc=new MISC();
        misc.setUid("Ytt2018*");
        Gson gson=new Gson();
        String body=gson.toJson(misc);
        OkHttpUtil client=new OkHttpUtil();
        String[] urls={//"https://testerhome.com",
                "https://www.baidu.com",
                 "http://www.qq.com",
               "http://www.tingyun.com"
               // "http://www.taobao.com",
               // "http://www.meituan.com",
               // "https://github.com/",
               // "http://www.google.com/"
        };
        try {
            for (int i=0;i<urls.length;i++){
                client.asyncPost(urls[i],body);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        TestCustomTrace();
    }
    void TestOkHttpPost(final  String url, final String body){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtil client=new OkHttpUtil();
                try {
                    String response = client.post(url, body);
                    System.out.println(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        config.waitSeconds(2);
    }
    void TestOkHttpGet(final String url){
/*        //beginTracer params cannot be ""
        NBSAppAgent.beginTracer("");*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtil client=new OkHttpUtil();
                try {
                    client.get(url);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
        config.waitSeconds(2);
/*        NBSAppAgent.endTracer("");*/
    }
    void TestCustomTrace(){
        TestNestedCustomTrace(2);

        /*TestMultiCustomTraceWithSameParam();*/
        /*TestCustomTraceAcrossFunction();*/
    }
    void TestNestedCustomTrace(int n){
        for (int i=0;i<n;i++){
/*            Log.d(TAG,""+i);*/
            NBSAppAgent.beginTracer(""+i);
            config.waitSeconds(1);
            }
        for (int j=n;j>0;j--){
/*            Log.d(TAG,""+(j-1));*/
            NBSAppAgent.endTracer(""+(j-1));
        }

/*        NBSAppAgent.beginTracer("1");
        config.waitSeconds(1);
        NBSAppAgent.beginTracer("2");
        config.waitSeconds(1);
        NBSAppAgent.beginTracer("3");
        config.waitSeconds(1);
        TestSyncJson();
        MISC misc=new MISC();
        Gson gson=new Gson();
        String body=gson.toJson(misc);
        TestOkHttpPost("https://testerhome.com",body);
        config.waitSeconds(1);
        NBSAppAgent.endTracer("3");
        NBSAppAgent.endTracer("2");
        NBSAppAgent.endTracer("1");*/
    }
    void TestMultiCustomTraceWithSameParam(){
        NBSAppAgent.beginTracer("1");
        config.waitSeconds(1);
        NBSAppAgent.endTracer("1");
        NBSAppAgent.beginTracer("1");
        config.waitSeconds(1);
        NBSAppAgent.endTracer("1");
        NBSAppAgent.beginTracer("2");
        config.waitSeconds(1);
        NBSAppAgent.endTracer("2");
    }
    void TestCustomTraceAcrossFunction(){
        func1();
        config.waitSeconds(1);
        func2();
    }
    void func1(){
        NBSAppAgent.beginTracer("*");
        config.waitSeconds(1);
    }
    void func2(){
        NBSAppAgent.endTracer("*");
    }
    void TestSQL(){
        SQLiteUtil dbOpenHelper =new SQLiteUtil(this);
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();
        for (int i=0;i<1000;i++){
            contentValues.put("book_name","name"+i);
            contentValues.put("author","author"+i);
            contentValues.put("price",12.9);
            long res = db.insert("bookStore", null, contentValues);// 插入数据
            if (res == -1) {
                Log.d(TAG,"DB insert  fail");
            } else {
                Log.d(TAG,"DB insert  success!");
            }
        }
        /*config.waitSeconds(1);*/

    }
    void addBook(){
        ArrayList<Book> books=new ArrayList<>();
        for(int i=0;i<1000;i++){
            Book book=new Book("name1","author1", BigDecimal.valueOf(12.9));
            books.add(book);
        }

        for(Book book:books){

        }

    }

    void buttonClickEvent(){
        ButtonOnClickListener listener=new ButtonOnClickListener();
        Button mbutton=(Button)findViewById(R.id.openActivity);
        mbutton.setOnClickListener(listener);
        Button btnActionState0=(Button)findViewById(R.id.btn_dasai);
        btnActionState0.setOnClickListener(listener);
        Button btnActionState1=(Button)findViewById(R.id.btn_crash);
        btnActionState1.setOnClickListener(listener);
        Button btnActionState2=(Button)findViewById(R.id.btn_network_error);
        btnActionState2.setOnClickListener(listener);
        Button btnActionState3=(Button)findViewById(R.id.btn_http_error);
        btnActionState3.setOnClickListener(listener);
        Button btnActionState4=(Button)findViewById(R.id.btn_slow_action);
        btnActionState4.setOnClickListener(listener);
        Button btnAsyncSlowAction=(Button)findViewById(R.id.btn_async_slow_action);
        btnAsyncSlowAction.setOnClickListener(listener);
    }
    private class ButtonOnClickListener implements View.OnClickListener{
        public int random(){
            Random r=new Random();
            int num=r.nextInt(2);
            return num;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.openActivity:
                    Login();break;
                case R.id.btn_dasai:
                    MqttRecv recv=new MqttRecv();
                    MQTTSend sender=new MQTTSend();
                    try {
                        Thread.sleep(100);
                        sender.send();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //openDasaiActivity();
                    break;
                case R.id.btn_crash:
                    Event.crash();
                    break;
                case R.id.btn_network_error:
                    Event.networkError();
                    Event.slowAction(4);
                    break;
                case R.id.btn_http_error:
                    Event.httpError();
                    break;
                case R.id.btn_slow_action:
                    Event.slowAction(4);
                    break;
                case R.id.btn_async_slow_action:
                    //TestOkHttpAsyncPost();
                    int flag=random();
                    //Context context=(flag==0?getApplicationContext():MainActivity.this);
                    Context context=getApplicationContext();
                    Event.asyncSlow(5,context);
                    break;
            }
        }
    }
    void openNewActivity(){
        Intent intent=new Intent();
        intent.setClass(this,Activity1.class);
        Log.d(TAG,"openNewActivity onCall...");
        MainActivity.this.startActivity(intent);
    }
    void openDasaiActivity(){
        Intent intent=new Intent();
        intent.setClass(this, DasaiActivity.class);
        Log.d(TAG,"openNewActivity onCall...");
        MainActivity.this.startActivity(intent);
    }
    void TestActivityLoopCall(){
        yttApplication app=(yttApplication)this.getApplicationContext();
        int count=app.getCount();
        Log.d(TAG,""+count);
        if(count<1){
            app.setCount(count+1);
            openNewActivity();
            config.waitSeconds(1);
        }
    }
    void TestAlertDilog(){
        new AlertDialog
                .Builder(this)
                .setTitle("标题")
                .setMessage("这是内容")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
    void TestOpenFragment(){
        Intent intent=new Intent();
        intent.setClass(this,FragmentRootActivity.class);
        Log.d(TAG,"FragmentRootActivity onCall...");
        MainActivity.this.startActivity(intent);
    }
    void Login(){
        Intent intent=new Intent();
        intent.setClass(this,LoginActivity.class);
        Log.d(TAG,"FragmentRootActivity onCall...");
        MainActivity.this.startActivity(intent);
    }
    int addCrash(){
        int[] count={0,1,2};
        return count[3];
    }
    void TestCrashBeforeTingyun(Context context){
        CrashWhenFirstStart(context);
        config.waitSeconds(1);
        intTingyun();
    }
    void TestCrashAfterTingyun(Context context){
        Log.d(TAG,"TestCrashAfterTingyun...");
        intTingyun();
        /*config.waitSeconds(3);*/
        CrashWhenFirstStart(context);
    }
    void CrashWhenFirstStart(Context context){
        /*
        can't catch crash before tingyun start
        * */
        SharedPreferences setting= context.getSharedPreferences("isFirstStartFlag",MODE_PRIVATE);
        Boolean isFirst=setting.getBoolean("isFirstStart",true);
        if(isFirst){
            setting.edit().putBoolean("isFirstStart",false).commit();
            Log.d(TAG,"First Start");
            addCrash();
        }else {
            Log.d(TAG,"Second Start");
        }
    }
    void openWebview(){
        Intent intent=new Intent();
        intent.setClass(this,TestWebview.class);
        Log.d(TAG,"TestWebview onCall...");
        MainActivity.this.startActivity(intent);
    }
}
