package com.hy.ggank.ui.data;

/**
 * Created by huyin on 2017/7/16.
 */

public interface BaseDadaListener<T> {
     void onSuccess(T data);

     void onError();
}
