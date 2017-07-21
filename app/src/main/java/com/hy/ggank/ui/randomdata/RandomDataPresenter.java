package com.hy.ggank.ui.randomdata;

import com.hy.ggank.ui.data.BaseDadaListener;
import com.hy.ggank.ui.data.DataResult;

/**
 * Created by huyin on 2017/7/20.
 */

public class RandomDataPresenter implements BaseDadaListener<DataResult> {

     RandomDataView mDataView;
     RandomDataModel randomData;

     public RandomDataPresenter(RandomDataView dataView) {
          this.mDataView = dataView;
          randomData = new RandomDataModel();
     }

     public void getData(String type, String count) {
          randomData.getData(type, count, this);
     }

     @Override
     public void onSuccess(DataResult data) {
          if (mDataView != null && data != null) {
               mDataView.onSuccess(data);
          }
     }

     @Override
     public void onError() {
          if (mDataView != null) {
               mDataView.onError();
          }
     }

     public void onDestory() {
          mDataView = null;
     }
}
