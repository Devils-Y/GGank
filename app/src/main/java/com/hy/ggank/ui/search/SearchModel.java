package com.hy.ggank.ui.search;

import com.hy.ggank.network.API;
import com.hy.ggank.network.HttpClient;
import com.hy.ggank.ui.data.BaseDadaListener;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huyin on 2017/7/23.
 */

public class SearchModel {
     Flowable<SearchResult> searchFlowable;
     BaseDadaListener<SearchResult> mSearchResultListener;

     public void getSearch(String keyword, String count, String page,
                           BaseDadaListener<SearchResult> searchResultListener) {
          this.mSearchResultListener = searchResultListener;
          searchFlowable = HttpClient.retrofit().create(API.SearchService.class)
                    .getSearch(keyword,count,page);
          searchFlowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<SearchResult>() {
                         @Override
                         public void onSubscribe(Subscription s) {
                              s.request(Long.MAX_VALUE);
                         }

                         @Override
                         public void onNext(SearchResult searchResult) {
                              mSearchResultListener.onSuccess(searchResult);
                         }

                         @Override
                         public void onError(Throwable t) {
                              mSearchResultListener.onError();
                         }

                         @Override
                         public void onComplete() {

                         }
                    });
     }
}
