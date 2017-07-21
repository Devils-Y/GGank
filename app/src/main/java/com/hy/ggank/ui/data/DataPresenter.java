package com.hy.ggank.ui.data;

/**
 * Created by huyin on 2017/7/16.
 */

public class DataPresenter implements BaseDadaListener<DataResult> {

     DataView mDataView;
     DataModel dataModel;

     public DataPresenter(DataView dataView) {
          this.mDataView = dataView;
          dataModel = new DataModel();
     }

     public void getData(String type, String count, String page) {
          dataModel.getData(type, count, page, this);
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
