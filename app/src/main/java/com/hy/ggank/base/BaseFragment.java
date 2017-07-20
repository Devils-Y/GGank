package com.hy.ggank.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huyin on 2017/4/30.
 */

public abstract class BaseFragment extends Fragment {

     public abstract int getLayoutId();

     private View contentView = null;

     Unbinder unbinder;

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

          //在这里数据内容加载到Fragment上
          if (contentView == null) {
               contentView = inflater.inflate(getLayoutId(), container, false);
          } else {
               return contentView;
          }
          unbinder = ButterKnife.bind(this, contentView);
          return contentView;
     }


     public View getContentView() {
          if (contentView != null) {

               return contentView;
          }
          return null;
     }

     @Override
     public void onDestroyView() {
          super.onDestroyView();
          unbinder.unbind();
          //移除当前视图，防止重复加载相同视图使得程序闪退
          ((ViewGroup) contentView.getParent()).removeView(contentView);
     }

}
