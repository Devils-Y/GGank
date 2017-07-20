package com.hy.ggank.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

import com.hy.ggank.utils.ImageUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by huyin on 2017/7/8.
 */

public class MyRecyclerView extends XRecyclerView implements AbsListView.OnScrollListener{

     Context mContext;

     public MyRecyclerView(Context context) {
          this(context, null);
     }

     public MyRecyclerView(Context context, AttributeSet attrs) {
          super(context, attrs);
          this.mContext = context;
     }

     @Override
     public void onScrollStateChanged(AbsListView view, int scrollState) {
          switch (scrollState) {
               case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
               case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    // 当RecyclerView处于静止状态时，继续加载图片
                    ImageUtils.getInstance().resumeRequests(mContext);
                    break;
               case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    // 当RecyclerView处于滑动状态时，停止加载图片，保证操作界面流畅
                    ImageUtils.getInstance().pauseRequests(mContext);
                    break;
          }
     }

     @Override
     public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

     }


}
