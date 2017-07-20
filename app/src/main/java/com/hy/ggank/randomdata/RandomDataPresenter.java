package com.hy.ggank.randomdata;

import com.hy.ggank.data.BaseDadaListener;
import com.hy.ggank.data.DataModel;
import com.hy.ggank.data.DataResult;
import com.hy.ggank.widget.CustomProgressDialog;

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
