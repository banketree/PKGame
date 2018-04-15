package com.game.banketree.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.game.banketree.R;
import com.game.banketree.components.CustomWebView;


public class BrowserActivity extends AppCompatActivity {
    private CustomWebView customWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customWebView = findViewById(R.id.customWebView);
        customWebView.setWebChromeClient(new WebChromeClient() {
        });
        customWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(BrowserActivity.this, BrowserActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                return true;
            }
        });

        String url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            customWebView.loadUrl("file:///android_asset/index.html");
        } else {
            customWebView.loadUrl(url);
        }
    }
}
