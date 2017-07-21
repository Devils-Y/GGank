package com.hy.ggank.ui.about;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseFragment;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/21.
 */

public class WebFragment extends BaseFragment {

     @BindView(R.id.webView)
     WebView webView;
     @BindView(R.id.progressBar)
     ProgressBar progressBar;

     String url;

     @Override
     public int getLayoutId() {
          return R.layout.fragment_web;
     }

     @Override
     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          url = getArguments().getString(URL);

          initView();
     }

     private void initView() {
          WebSettings webSettings = webView.getSettings();
          webSettings.setDomStorageEnabled(true);
          webSettings.setJavaScriptEnabled(true);
          webSettings.setAppCacheEnabled(false);
          webSettings.setLoadWithOverviewMode(true);
          webView.clearCache(true);//支持缓存
          webView.loadUrl(url);
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
                    super.onProgressChanged(view, newProgress);
                    if (newProgress == 100) {
                         progressBar.setVisibility(View.GONE);
                    } else {
                         progressBar.setVisibility(View.VISIBLE);
                    }
               }
          });
     }

     @Override
     public void onPause() {
          webView.reload();
          super.onPause();
     }

     @Override
     public void onDestroyView() {
          super.onDestroyView();
          if(webView!=null){
               webView.removeAllViews();
               webView.destroy();
          }
     }
}
