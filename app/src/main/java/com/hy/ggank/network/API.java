package com.hy.ggank.network;



import com.hy.ggank.data.DataResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by huyin on 2017/7/5.
 */

public class API {
     public static final String HEAD_URL = "http://gank.io/api/";

     /**
      * 分类数据
      */
     public static final String DataURL = "data/{type}/{count}/{page}";

     public interface DataService {
          @GET(API.DataURL)
          Flowable<DataResult> getData(@Path("type") String type,
                                       @Path("count") String count,
                                       @Path("page") String page);
     }

     /**
      * 随机数据
      */
     public static final String RandomDataURL = "random/data/{type}/{count}";

     public interface RandomDataService{
          @GET(API.RandomDataURL)
          Flowable<DataResult> getData(@Path("type") String type,
                                       @Path("count") String count);
     }
}
