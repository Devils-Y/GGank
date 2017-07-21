package com.hy.ggank.ui.data;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseFragment;
import com.hy.ggank.widget.MyRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.TYPE;
import static com.jcodecraeer.xrecyclerview.ProgressStyle.Pacman;
import static com.jcodecraeer.xrecyclerview.ProgressStyle.SquareSpin;

/**
 * Created by huyin on 2017/7/6.
 */

public class Data4ImageFragment extends BaseFragment implements DataView {

     @BindView(R.id.recyclerView)
     MyRecyclerView recyclerView;

     DataPresenter dataPresenter;
     ArrayList<DataResult.ResultsBean> resultsLists;
     Data4ImageAdapter data4ImageAdapter;

     private static final String COUNT = "10";//每页的item数

     private int page = 1;

     private String requestData;


     @Override
     public int getLayoutId() {
          return R.layout.fragment_data;
     }

     @Override
     public void onActivityCreated(Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          requestData = getArguments().getString(TYPE);
          initView();
     }

     @Override
     public void onResume() {
          super.onResume();
          initData();
          recyclerView.scrollTo(0, 0);
          recyclerView.refresh();
     }

     private void initData() {
          dataPresenter = new DataPresenter(this);
          dataPresenter.getData(requestData, COUNT, String.valueOf(page));
     }

     private void initView() {
          resultsLists = new ArrayList<DataResult.ResultsBean>();
          data4ImageAdapter = new Data4ImageAdapter(getActivity(), resultsLists);

          StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL);
          recyclerView.setLayoutManager(staggeredGridLayoutManager);
          ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
          recyclerView.setAdapter(data4ImageAdapter);
          recyclerView.setRefreshProgressStyle(Pacman);
          recyclerView.setLoadingMoreProgressStyle(SquareSpin);
          recyclerView.setPullRefreshEnabled(true);
          recyclerView.setLoadingMoreEnabled(true);
          recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                         public void run() {
                              data4ImageAdapter.clear();
                              page = 1;
                              dataPresenter.getData(requestData, COUNT, String.valueOf(page));
                              recyclerView.refreshComplete();
                         }
                    }, 1000);
               }

               @Override
               public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                         public void run() {
                              page++;
                              dataPresenter.getData(requestData, COUNT, String.valueOf(page));
                              recyclerView.loadMoreComplete();
                         }
                    }, 100);
               }
          });
     }

     @Override
     public void onSuccess(DataResult dataResult) {
          data4ImageAdapter.add(dataResult.getResults());
     }

     @Override
     public void onError() {

     }

     @Override
     public void onDestroyView() {
          super.onDestroyView();
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
