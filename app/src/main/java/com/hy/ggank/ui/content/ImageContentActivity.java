package com.hy.ggank.ui.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.utils.ImageUtils;
import com.hy.ggank.ui.welfare.WelfareActivity;
import com.hy.ggank.widget.CustomProgressDialog;

import java.lang.ref.WeakReference;

import butterknife.BindString;
import butterknife.BindView;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.IMAGE_WELFARE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/20.
 * <p>
 * 图片详情
 */

public class ImageContentActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.image)
     ImageView image;

     @BindString(R.string.image_content_txt)
     String imageContentTxt;

     String url, imageType;
     DisplayMetrics dm;

     CustomProgressDialog customProgressDialog;

     @Override
     public int getLayoutID() {
          return R.layout.activity_image_content;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          url = getIntent().getStringExtra(URL);
          imageType = getIntent().getStringExtra(IMAGE_TYPE);

          dm = new DisplayMetrics();
          getWindowManager().getDefaultDisplay().getMetrics(dm);

          customProgressDialog = new CustomProgressDialog(this);
          customProgressDialog.show();
          initData();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.image_content_menu, menu);
          return true;
     }

     /**
      * menu菜单点击操作的监听事件
      */
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {
               case R.id.more_welfare:
                    startActivity(WelfareActivity.class);
                    break;
          }
          return super.onOptionsItemSelected(item);
     }

     @Override
     public boolean onPrepareOptionsMenu(Menu menu) {
          // 动态设置ToolBar状态
          switch (imageType) {
               case IMAGE_WELFARE:
                    menu.findItem(R.id.more_welfare).setVisible(true);
                    break;
               case IMAGE_OTHER:
                    menu.findItem(R.id.more_welfare).setVisible(false);
                    break;
          }
          return super.onPrepareOptionsMenu(menu);
     }


     @Override
     public void initTitle() {
          toolbar.setTitle(imageContentTxt);
          setSupportActionBar(toolbar);
          toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (isFastDoubleClick()) {
                         return;
                    }
                    finish();
               }
          });
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          setImmerseLayout(toolbar);
     }

     private void initData() {
          WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(image);
          ImageView target = imageViewWeakReference.get();
          if (url != null) {
               ImageUtils.getInstance().displayImageUseFitWidth(this, new StringBuffer()
                         .append(url).append("?imageView2/0/w/").append(dm.widthPixels).toString(), target);
               if (customProgressDialog != null) {
                    customProgressDialog.dismiss();
               }
          }
     }

     /**
      * 设置singleTask
      * <p>
      * 重写onNewIntent
      * 注意：必须调用setIntent(intent);
      *
      * @param intent
      */
     @Override
     protected void onNewIntent(Intent intent) {
          super.onNewIntent(intent);
          setIntent(intent);
          url = getIntent().getStringExtra(URL);
          imageType = getIntent().getStringExtra(IMAGE_TYPE);
          initData();
     }

     @Override
     public void onBackPressed() {
          super.onBackPressed();
          if (isFastDoubleClick()) {
               return;
          }
          finish();
     }
}
