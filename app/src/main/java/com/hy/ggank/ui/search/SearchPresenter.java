package com.hy.ggank.ui.search;

import com.hy.ggank.ui.data.BaseDadaListener;
import com.hy.ggank.utils.ToastUtils;

/**
 * Created by huyin on 2017/7/23.
 */

public class SearchPresenter implements BaseDadaListener<SearchResult> {

     SearchView mSearchView;
     SearchModel searchModel;

     public SearchPresenter(SearchView searchView) {
          this.mSearchView = searchView;
          searchModel = new SearchModel();
     }

     public void getSearch(String keyword, String count, String page) {
          searchModel.getSearch(keyword, count, page, this);
     }

     @Override
     public void onSuccess(SearchResult data) {
          if (mSearchView != null) {
               if (data.getResults().length != 0) {
                    mSearchView.onSuccess(data);
               } else {
                    ToastUtils.toast("已经到底了!");
               }
          }
     }

     @Override
     public void onError() {
          if (mSearchView != null) {
               mSearchView.onError();
          }
     }

     public void onDestory() {
          mSearchView = null;
     }
}
