package com.banketree.pk10game.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.banketree.pk10game.R;
import com.banketree.pk10game.components.CustomWebView;
import com.banketree.pk10game.components.TitleBar;


public class BrowserActivity extends AppCompatActivity {
    private CustomWebView customWebView;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customWebView = findViewById(R.id.customWebView);
        titleBar = findViewById(R.id.title_bar);
        initTitle();
        initData();

        String url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            customWebView.loadUrl("file:///android_asset/index.html");
            titleBar.getLeftTextView().setVisibility(View.GONE);
        } else {
            customWebView.loadUrl(url);
        }
    }

    private void initTitle() {
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAbout();
            }
        });
    }

    private void initData() {
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
    }

    private void showAbout() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("关于")//设置对话框的标题
                .setMessage(R.string.app_about)//设置对话框的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
