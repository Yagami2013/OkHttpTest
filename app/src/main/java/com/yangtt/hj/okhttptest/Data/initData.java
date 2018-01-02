package com.yangtt.hj.okhttptest.Data;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * Created by hj on 2017/12/14.
 */

public class initData {
   public String[] app={"com.yangtt.hj.okhttptest","OkHttpTest", "1.0","a3e594f2-eb0a-4489-8cd1-57b1e951ba0a",""};
   public String[] dev={};
   public String did="-7733679013179391444";

    public String getDid() {
        return did;
    }

    public String[] getApp() {
        return app;
    }

    public String[] getDev() {
        return dev;
    }

    public void setApp(String[] app) {
        this.app = app;
    }

    public void setDev(String[] dev) {
        this.dev = dev;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
