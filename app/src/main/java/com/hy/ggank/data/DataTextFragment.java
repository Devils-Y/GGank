package com.hy.ggank.data;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.hy.ggank.R;
import com.hy.ggank.base.BaseFragment;
import com.hy.ggank.base.LinearSpaceItemDecoration;
import com.hy.ggank.widget.MyRecyclerView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.TYPE;

/**
 * Created by huyin on 2017/7/16.
 */

public class DataTextFragment extends BaseFragment implements DataView {

     @BindView(R.id.recyclerView)
     MyRecyclerView recyclerView;

     DataPresenter dataPresenter;


     private static final String COUNT = "10";//每页的item数

     private int page = 1;

     private String requestData;

     Data4GraphicAdapter data4GraphicAdapter;
     ArrayList<DataResult.ResultsBean> resultsLists;

     @Override
     public int getLayoutId() {
          return R.layout.fragment_data;
     }

     @Override
     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          requestData = getArguments().getString(TYPE);
          initView();
          initData();
     }


     @Override
     public void onResume() {
          super.onResume();
     }

     private void initData() {
          dataPresenter = new DataPresenter(this);
          recyclerView.refresh();
          dataPresenter.getData(requestData, COUNT, String.valueOf(page));
     }

     private void initView() {
          resultsLists = new ArrayList<>();
          data4GraphicAdapter = new Data4GraphicAdapter(getActivity(), resultsLists);
          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.VERTICAL, false);
          recyclerView.setLayoutManager(linearLayoutManager);
          recyclerView.addItemDecoration(new LinearSpaceItemDecoration(getActivity(),
                    LinearSpaceItemDecoration.VERTICAL_LIST));
          recyclerView.setAdapter(data4GraphicAdapter);
          recyclerView.setPullRefreshEnabled(true);
          recyclerView.setLoadingMoreEnabled(true);
          recyclerView.setRefreshProgressStyle(ProgressStyle.CubeTransition);
          recyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
          recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                              page = 1;
                              data4GraphicAdapter.clear();
                              dataPresenter.getData(requestData, COUNT, String.valueOf(page));
                              if (recyclerView != null) {
                                   recyclerView.refreshComplete();
                              }
                         }
                    }, 1000);
               }

               @Override
               public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                         public void run() {
                              page++;
                              dataPresenter.getData(requestData, COUNT, String.valueOf(page));
                              if (recyclerView != null) {
                                   recyclerView.loadMoreComplete();
                              }
                         }
                    }, 50);
               }
          });
     }

     @Override
     public void onSuccess(DataResult dataResult) {
          data4GraphicAdapter.add(dataResult.getResults());
     }

     @Override
     public void onError() {

     }

     @Override
     public void onDestroy() {
          super.onDestroy();
          dataPresenter.onDestory();
          unbindDrawables(getContentView().findViewById(R.id.fragment));
     }

     private void unbindDrawables(View view) {
          if (view.getBackground() != null) {
               view.getBackground().setCallback(null);
          }
          if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
               for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
               }
               ((ViewGroup) view).removeAllViews();
          }
     }
}
