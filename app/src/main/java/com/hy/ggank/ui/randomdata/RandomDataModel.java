package com.hy.ggank.ui.randomdata;

import com.hy.ggank.ui.data.BaseDadaListener;
import com.hy.ggank.ui.data.DataResult;
import com.hy.ggank.network.API;
import com.hy.ggank.network.HttpClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huyin on 2017/7/20.
 */

public class RandomDataModel {
     Flowable<DataResult> dataResultFlowable;
     BaseDadaListener<DataResult> mDataModelListener;

     public void getData(String type, String count, BaseDadaListener<DataResult> dataModelListener) {
          this.mDataModelListener = dataModelListener;
          dataResultFlowable = HttpClient.retrofit().create(API.RandomDataService.class)
                    .getData(type, count);
          dataResultFlowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DataResult>() {
                         @Override
                         public void onSubscribe(Subscription s) {
                              s.request(Long.MAX_VALUE);
                         }

                         @Override
                         public void onNext(DataResult dataResult) {
                              mDataModelListener.onSuccess(dataResult);
                         }

                         @Override
                         public void onError(Throwable t) {
                              mDataModelListener.onError();
                         }

                         @Override
                         public void onComplete() {

                         }
                    });
     }
}
