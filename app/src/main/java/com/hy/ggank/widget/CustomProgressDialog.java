package com.hy.ggank.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.hy.ggank.R;

/**
 * Created by huyin on 2017/7/20.
 */

public class CustomProgressDialog extends ProgressDialog{

     public CustomProgressDialog(Context context) {
          super(context);
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.layout_custom_progress);
     }
}
