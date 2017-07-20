package com.hy.ggank.search;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.widget.MyRecyclerView;

import butterknife.BindView;

/**
 * Created by huyin on 2017/7/20.
 * <p>
 * 搜索
 */

public class SearchActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.searchRecyclerView)
     MyRecyclerView searchRecyclerView;

     @Override
     public int getLayoutID() {
          return R.layout.activity_search;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
     }

     @Override
     public void initTitle() {
          toolbar.setTitle(getString(R.string.search_txt));
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
}
