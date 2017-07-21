package com.hy.ggank.ui.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.db.CollectionManager;
import com.hy.ggank.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hy.ggank.constants.Constants.COLLECTION_CHANGE;
import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;
import static com.hy.ggank.ui.data.Data4GraphicAdapter.collectionModel;


/**
 * Created by huyin on 2017/7/18.
 */

public class DescContentActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.webView)
     WebView webView;
     @BindView(R.id.collection)
     FloatingActionButton collection;
     @BindView(R.id.progressBar)
     ProgressBar progressBar;

     @OnClick(R.id.collection)
     void onClick(View view) {
          switch (view.getId()) {
               case R.id.collection:
                    if (!isCollection) {
                         boolean isAdd = collectionManager.addCollection(
                                   collectionModel.getCreatedAt(),
                                   collectionModel.getDesc(),
                                   collectionModel.getPublishedAt(),
                                   collectionModel.getSource(),
                                   collectionModel.getType(),
                                   collectionModel.getWho(),
                                   collectionModel.getUrl(),
                                   collectionModel.getImage());
                         if(isAdd){
                              ToastUtils.toast(getString(R.string.collection_success_txt));
                              isCollection = true;
                              collection.setImageResource(R.drawable.ic_star_white_24dp);
                         }else{
                              ToastUtils.toast(getString(R.string.collection_failed_txt));
                         }
                    } else {
                         boolean isDelete = collectionManager.delete(webUrl);
                         if(isDelete){
                              ToastUtils.toast(getString(R.string.cancel_collection_success_txt));
                              isCollection = false;
                              collection.setImageResource(R.drawable.ic_star_border_white_24dp);
                         }else{
                              ToastUtils.toast(getString(R.string.cancel_collection_failed_txt));
                         }
                    }
                    //发送设置完成广播
                    Intent intent = new Intent(COLLECTION_CHANGE);
                    LocalBroadcastManager.getInstance(DescContentActivity.this).sendBroadcast(intent);
                    break;
          }
     }

     String toolTitle, webUrl;

     CollectionManager collectionManager;

     private boolean isCollection = false;


     @Override
     public int getLayoutID() {
          return R.layout.activity_desc_content;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          toolTitle = getIntent().getStringExtra(TOOLTITLE);
          webUrl = getIntent().getStringExtra(URL);

          collectionManager = new CollectionManager(this);
          initData();
          initView();
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
                         if(webView!=null){
                              webView.removeAllViews();
                              webView.destroy();
                         }
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

     private void initView() {
          if (collectionManager.queryByUrl(webUrl).size() > 0) {
               isCollection = true;
               collection.setImageResource(R.drawable.ic_star_white_24dp);
          } else {
               isCollection = false;
               collection.setImageResource(R.drawable.ic_star_border_white_24dp);
          }
     }

     @Override
     public void onPause() {
          webView.reload();
          super.onPause();
     }

     @Override
     protected void onDestroy() {
          super.onDestroy();
          if(webView!=null){
               webView.removeAllViews();
               webView.destroy();
          }
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
               if(webView!=null){
                    webView.removeAllViews();
                    webView.destroy();
               }
               finish();
          }
     }
}
