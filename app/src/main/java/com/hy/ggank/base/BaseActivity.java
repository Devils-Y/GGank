package com.hy.ggank.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.hy.ggank.utils.ScreenUtils;

import butterknife.ButterKnife;

/**
 * Created by huyin on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(getLayoutID());
          ButterKnife.bind(this);
          initTitle();
     }

     //沉浸式
     protected void setImmerseLayout(View view) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
               Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
               window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

               int statusBarHeight = ScreenUtils.getStatusBarHeight(getBaseContext());
               view.setPadding(0, statusBarHeight, 0, 0);
          }
     }

     public abstract int getLayoutID();

     public abstract void initTitle();


     /**
      * 跳转下一个Activity,并传值
      *
      * @param targetActivity
      * @param bundle
      */
     public void startActivity(Class<?> targetActivity, Bundle bundle) {
          Intent intent = new Intent(this, targetActivity);
          if (bundle != null) {
               intent.putExtras(bundle);
          }
          startActivity(intent);
     }

     /**
      * 跳转下一个Activity,并设置返回码
      *
      * @param targetActivity
      * @param RequestCode
      */
     public void startActivityForResult(Class<?> targetActivity, int RequestCode) {
          Intent intent = new Intent(this, targetActivity);
          startActivityForResult(intent, RequestCode);
     }

     /**
      * 带参数返回上一个Activity,并关闭当前Activity
      *
      * @param name
      * @param value
      */
     public void setActivityResult(String name, String value) {
          Intent intent = new Intent();
          intent.putExtra(name, value);
          setResult(RESULT_OK, intent);
          this.finish();
     }

     /**
      * 跳转下一个Activity
      *
      * @param targetActivity
      */
     public void startActivity(Class<?> targetActivity) {
          startActivity(targetActivity, null);
     }

     /**
      * 跳转下一个Activity,并关闭当前Activity
      *
      * @param targetActivity
      */
     public void startActivityAndCloseThis(Class<?> targetActivity) {
          startActivity(targetActivity);
          this.finish();
     }

     /**
      * 按钮最后一次点击时间
      */
     private static long mLastClickTime = 0;
     /**
      * 空闲时间
      */
     private static final int SPACE_TIME = 1000;

     /**
      * 是否连续点击按钮多次
      *
      * @return 是否快速多次点击
      */
     public static boolean isFastDoubleClick() {
          long time = SystemClock.elapsedRealtime();
          if (time - mLastClickTime <= SPACE_TIME) {
               return true;
          } else {
               mLastClickTime = time;
               return false;
          }
     }
}
