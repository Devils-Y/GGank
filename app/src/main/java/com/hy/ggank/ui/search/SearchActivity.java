package com.hy.ggank.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.*;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseDividerItemDecoration;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.widget.CustomProgressDialog;
import com.hy.ggank.widget.MyRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jcodecraeer.xrecyclerview.ProgressStyle.Pacman;
import static com.jcodecraeer.xrecyclerview.ProgressStyle.SquareSpin;

/**
 * Created by huyin on 2017/7/20.
 * <p>
 * 搜索
 */

public class SearchActivity extends BaseSwipeActivity implements com.hy.ggank.ui.search.SearchView {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.searchView)
     SearchView searchView;
     @BindView(R.id.searchRecyclerView)
     MyRecyclerView searchRecyclerView;

     SearchPresenter searchPresenter;

     private static final String COUNT = "10";//每页的item数

     private int page = 1;

     SearchAdapter searchAdapter;
     List<SearchResult.ResultsBean> searchLists;

     CustomProgressDialog cProgressDialog;

     String queryText;

     @Override
     public int getLayoutID() {
          return R.layout.activity_search;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          cProgressDialog = new CustomProgressDialog(this);
          searchPresenter = new SearchPresenter(this);
          searchLists = new ArrayList<>();
          initView();
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

     private void initView() {
          //设置我们的SearchView
          searchView.setIconifiedByDefault(true);//设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
          searchView.onActionViewExpanded();// 写上此句后searchView初始是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能出现输入框,也就是设置为ToolBar的ActionView，默认展开
          searchView.requestFocus();//输入焦点
          searchView.setSubmitButtonEnabled(true);//添加提交按钮，监听在OnQueryTextListener的onQueryTextSubmit响应
          searchView.setFocusable(true);//将控件设置成可获取焦点状态,默认是无法获取焦点的,只有设置成true,才能获取控件的点击事件
          searchView.setIconified(false);//输入框内icon不显示
          searchView.requestFocusFromTouch();//模拟焦点点击事件

          searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
          searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要

          //事件监听
          searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String query) {
                    queryText = query;
                    searchPresenter.getSearch(query, COUNT, String.valueOf(page));
                    cProgressDialog.show();
                    return false;
               }

               @Override
               public boolean onQueryTextChange(String newText) {
                    if (TextUtils.isEmpty(newText)) {
                         searchAdapter.clear();
                    }
                    return false;
               }
          });

          searchAdapter = new SearchAdapter(this, searchLists);
          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false);
          searchRecyclerView.setLayoutManager(linearLayoutManager);
          ((SimpleItemAnimator) searchRecyclerView.getItemAnimator())
                    .setSupportsChangeAnimations(false);
          searchRecyclerView.addItemDecoration(new BaseDividerItemDecoration(this,
                    BaseDividerItemDecoration.VERTICAL));
          searchRecyclerView.setAdapter(searchAdapter);
          searchRecyclerView.setPullRefreshEnabled(false);
          searchRecyclerView.setLoadingMoreEnabled(true);
          searchRecyclerView.setRefreshProgressStyle(Pacman);
          searchRecyclerView.setLoadingMoreProgressStyle(SquareSpin);
          searchRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
               }

               @Override
               public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                         public void run() {
                              page++;
                              searchPresenter.getSearch(queryText, COUNT, String.valueOf(page));
                              if (searchRecyclerView != null) {
                                   searchRecyclerView.loadMoreComplete();
                              }
                         }
                    }, 50);
               }
          });
     }

     @Override
     public void onSuccess(SearchResult searchResult) {
          searchAdapter.add(searchResult.getResults());
          cProgressDialog.dismiss();
          searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
          searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要
     }

     @Override
     public void onError() {

     }

     @Override
     protected void onDestroy() {
          super.onDestroy();
          searchPresenter.onDestory();
     }
}
