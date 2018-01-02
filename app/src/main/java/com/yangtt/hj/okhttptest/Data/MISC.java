package com.yangtt.hj.okhttptest.Data;

/**
 * Created by hj on 2017/12/14.
 */

public class MISC {
    String uid;
    int size=0;
    String aid;
    String im;
    String mac;

    public int getSize() {
        return size;
    }

    public String getAid() {
        return aid;
    }

    public String getIm() {
        return im;
    }

    public String getMac() {
        return mac;
    }

    public String getUid() {
        return uid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
