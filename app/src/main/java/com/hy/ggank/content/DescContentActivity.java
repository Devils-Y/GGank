package com.hy.ggank.content;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseSwipeActivity;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;


/**
 * Created by huyin on 2017/7/18.
 */

public class DescContentActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.webView)
     WebView webView;
     @BindView(R.id.progressBar)
     ProgressBar progressBar;

     String toolTitle, webUrl;


     @Override
     public int getLayoutID() {
          return R.layout.activity_desc_content;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          toolTitle = getIntent().getStringExtra(TOOLTITLE);

          webUrl = getIntent().getStringExtra(URL);
          initData();
     }

     @Override
     public void initTitle() {
          setSupportActionBar(toolbar);
          toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (isFastDoubleClick()) {
                         return;
                    }
                    if (webView.canGoBack()) {
                         webView.goBack();
                    } else {
                         finish();
                    }
               }
          });
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          setImmerseLayout(toolbar);
     }

     @Override
     protected void onResume() {
          super.onResume();
          toolbar.setTitle(toolTitle);
     }

     private void initData() {

          // 在当前的浏览器中响应
          WebSettings webSettings = webView.getSettings();
          webSettings.setDomStorageEnabled(true);
          webSettings.setJavaScriptEnabled(true);
          webSettings.setAppCacheEnabled(false);
          webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
          webSettings.setLoadWithOverviewMode(true);

          webView.clearCache(true);//支持缓存
          webView.loadUrl(webUrl);
          webView.setWebViewClient(new WebViewClient() {
               @Override
               public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
               }
          });

          webView.setWebChromeClient(new WebChromeClient() {
               @Override
               public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                         progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                    } else {
                         progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                         progressBar.setProgress(newProgress);//设置进度值
                    }

               }
          });
     }

     @Override
     public void onBackPressed() {
          super.onBackPressed();
          if (isFastDoubleClick()) {
               return;
          }
          if (webView.canGoBack()) {
               webView.goBack();
          } else {
               finish();
          }
     }
}
