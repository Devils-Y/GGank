package com.hy.ggank.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by huyin on 2017/7/5.
 */

public class MyApplication extends Application {

     private static MyApplication myApplication = null;

     public MyApplication() {
     }

     public static MyApplication getIntances() {
          if (myApplication == null) {
               synchronized (MyApplication.class){
                    if(myApplication == null){
                         myApplication = new MyApplication();
                    }
               }
          }
          return myApplication;
     }

     private static Context context;


     public static Context getContext() {
          return context;
     }

     @Override
     public void onCreate() {
          super.onCreate();
          context = getApplicationContext();
     }
}
