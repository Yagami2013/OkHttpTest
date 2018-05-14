package com.yangtt.hj.okhttptest;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.networkbench.agent.impl.instrumentation.NBSWebChromeClient;

/**
 * Created by hj on 2018/1/10.
 */

public class TestWebview extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        WebView.setWebContentsDebuggingEnabled(true);
    }

    WebView webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient(){


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    });
        webview.setWebChromeClient(new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            NBSWebChromeClient.initJSMonitor(view, newProgress);
            super.onProgressChanged(view, newProgress);
        }

    });
    WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.loadUrl("http://shop.fanli.com/h5/licai?spm=sse.pc.module-loc5-67&devid=61871669169260&c_aver=1.0&c_src=2&c_v=6.5.0.35&abtest=60773_a-412_b-368_a-4_a-80_b-28_a-24_b-2_b-24_b-58_d-44_b-22_b-58_a-2_b-22_a-18_b-4_c-2_a-18_a-4_b-46_a-10_a-44_a-28_b-3c8a&c_nt=wifi&mc=1");

}

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


