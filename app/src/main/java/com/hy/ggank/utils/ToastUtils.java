package com.hy.ggank.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hy.ggank.application.MyApplication;
import com.hy.ggank.widget.CustomToast;

public class ToastUtils {

     public static void toast(final String content) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {

               @Override
               public void run() {
                    CustomToast.showToast(MyApplication.getContext(), content, 1000);
               }
          });
     }

     public static void toast(final int resID) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {

               @Override
               public void run() {
                    CustomToast.showToast(MyApplication.getContext(), resID, 1000);
               }
          });
     }

     public static void toast(final Context context, final String content) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {

               @Override
               public void run() {
                    CustomToast.showToast(context, content, 1000);
               }
          });
     }

     public static void toast(final Context context, final int resID) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {

               @Override
               public void run() {
                    CustomToast.showToast(context, resID, 1000);
               }
          });
     }


     public static void longToast(final Context context, final String content) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {

               @Override
               public void run() {
                    CustomToast.showToast(context, content, 3000);
               }
          });
     }

}
