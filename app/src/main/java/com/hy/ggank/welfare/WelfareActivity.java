package com.hy.ggank.welfare;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.data.Data4ImageFragment;

import butterknife.BindString;
import butterknife.BindView;

import static com.hy.ggank.constants.Constants.TYPE;


/**
 * Created by huyin on 2017/7/18.
 * <p>
 * 更多福利
 */

public class WelfareActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.frameLayout)
     FrameLayout frameLayout;

     @BindString(R.string.welfare_txt)
     String welfareTxt;

     Bundle bundle;
     Data4ImageFragment data4ImageFragment;

     @Override
     public int getLayoutID() {
          return R.layout.activity_welfare;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          initView();
     }

     @Override
     public void initTitle() {
          toolbar.setTitle(welfareTxt);
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

     private void initView() {
          bundle = new Bundle();
          bundle.putString(TYPE, welfareTxt);
          data4ImageFragment = new Data4ImageFragment();
          data4ImageFragment.setArguments(bundle);
          FragmentManager fragmentManager = getSupportFragmentManager();
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.frameLayout, data4ImageFragment);
          fragmentTransaction.commitAllowingStateLoss();
     }
}
