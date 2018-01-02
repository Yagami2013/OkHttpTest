package com.yangtt.hj.okhttptest.Util;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.util.Log;

import com.yangtt.hj.okhttptest.Config;

/**
 * Created by hj on 2017/12/19.
 */

public class SQLiteUtil extends SQLiteOpenHelper {
    Config config =new Config();
    String TAG= config.getTAG();
    private static final String DATABASE_NAME = "book_store.db";//数据库名字
    private static final int DATABASE_VERSION = 1;//数据库版本号
    private static final String CREATE_TABLE = "create table bookStore ("
            + "id integer primary key autoincrement,"
            + "book_name text, "
            + "author text, "
            + "price real)";//数据库里的表

    public SQLiteUtil(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteUtil(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);//调用到SQLiteOpenHelper中
        Log.d(TAG,"New CustomSQLiteOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}