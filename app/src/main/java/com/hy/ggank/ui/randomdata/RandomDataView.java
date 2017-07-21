package com.hy.ggank.ui.randomdata;

import com.hy.ggank.ui.data.DataResult;

/**
 * Created by huyin on 2017/7/16.
 */

public interface RandomDataView {
     void onSuccess(DataResult dataResult);

     void onError();
}
