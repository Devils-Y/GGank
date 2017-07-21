package com.hy.ggank.ui.data;

/**
 * Created by huyin on 2017/7/5.
 */

public class DataResult {

     /**
      * error : false
      * results : [{"_id":"594561ad421aa90f1036ac3e","createdAt":"2017-06-18T01:06:53.2Z","desc":"Android实现反人类音量滑块","images":["http://img.gank.io/81d028d4-0741-4497-8032-e00605ba06a9"],"publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"https://github.com/shellljx/FuckingVolumeSlider","used":true,"who":"li jinxiang"},{"_id":"5948dbad421aa90f1036ac51","createdAt":"2017-06-20T16:24:13.357Z","desc":"Android界面性能调优手册","publishedAt":"2017-07-05T11:15:30.556Z","source":"chrome","type":"Android","url":"https://androidtest.org/android-graphics-performance-pattens/","used":true,"who":"tank"},{"_id":"594b187e421aa90ca209c3a3","createdAt":"2017-06-22T09:08:14.525Z","desc":"那些年Android黑科技②:欺骗的艺术","publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247485080&idx=1&sn=fc53738f874746bb0df7ed47b3eeb667&chksm=96cda7d5a1ba2ec3148becbc9e6f0ae78c887287357956ec2f8d5cb1e63f7ca93d9cf2982748#rd","used":true,"who":"陈宇明"},{"_id":"595b59e7421aa90ca3bb6a9b","createdAt":"2017-07-04T17:03:35.743Z","desc":"PopsTabView是个filter容器,他可以自动,快速,构建不同筛选样式,自由组合成一组tab.","images":["http://img.gank.io/c51966c2-309d-4b78-9da4-4cbb04217e28"],"publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"https://github.com/ccj659/PopsTabView","used":true,"who":"Chauncey"},{"_id":"595b6634421aa90ca3bb6a9c","createdAt":"2017-07-04T17:56:04.67Z","desc":"用编译时注解生成代理类来管理Retrofit Call的生命周期","publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"https://github.com/luckyandyzhang/RetrofitLifecycle","used":true,"who":"Andy Zhang"},{"_id":"595c3db1421aa90ca209c3f1","createdAt":"2017-07-05T09:15:29.322Z","desc":"使用TabLayout看这篇就够了","publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247485476&idx=1&sn=f6d28e484e574fc9196f1aece5017827&chksm=96cda969a1ba207f99a37ae36c76c32cf7d276382be7bb58949187a3596be202407506bfb066#rd","used":true,"who":"陈宇明"},{"_id":"595c4fa3421aa90c9203d32b","createdAt":"2017-07-05T10:32:03.638Z","desc":"重新理解响应式编程","images":["http://img.gank.io/894e2a22-f76b-4550-a476-54f14f0cb1cb"],"publishedAt":"2017-07-05T11:15:30.556Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/c95e29854cb1","used":true,"who":"ladingwu"},{"_id":"595ad074421aa90ca3bb6a90","createdAt":"2017-07-04T07:17:08.609Z","desc":"Android 有两套相机 Api，使用起来很麻烦，好在 Foto 开源了他们在 Android 上的 Camera 封装 Api，力荐！","images":["http://img.gank.io/0a15bae7-c513-4feb-bbe2-1273b8b809ce"],"publishedAt":"2017-07-04T11:50:36.484Z","source":"chrome","type":"Android","url":"https://github.com/Fotoapparat/Fotoapparat","used":true,"who":"代码家"},{"_id":"595ad096421aa90cb4724b5b","createdAt":"2017-07-04T07:17:42.635Z","desc":"MD 风格的日历组件，很精致哦。","images":["http://img.gank.io/75a6251f-ffaf-41dc-8dbc-fa58802b0d8e"],"publishedAt":"2017-07-04T11:50:36.484Z","source":"chrome","type":"Android","url":"https://github.com/Applandeo/Material-Calendar-View","used":true,"who":"代码家"},{"_id":"595ad0d4421aa90cb4724b5c","createdAt":"2017-07-04T07:18:44.154Z","desc":"非常 Fancy 的选项过滤器。","images":["http://img.gank.io/f9e1e0ef-88fc-4e02-8620-2cf1700966c5"],"publishedAt":"2017-07-04T11:50:36.484Z","source":"chrome","type":"Android","url":"https://github.com/Krupen/FabulousFilter","used":true,"who":"代码家"}]
      */

     private boolean error;
     private ResultsBean[] results;

     public boolean isError() {
          return error;
     }

     public void setError(boolean error) {
          this.error = error;
     }

     public ResultsBean[] getResults() {
          return results;
     }

     public void setResults(ResultsBean[] results) {
          this.results = results;
     }

     public static class ResultsBean {
          /**
           * _id : 594561ad421aa90f1036ac3e
           * createdAt : 2017-06-18T01:06:53.2Z
           * desc : Android实现反人类音量滑块
           * images : ["http://img.gank.io/81d028d4-0741-4497-8032-e00605ba06a9"]
           * publishedAt : 2017-07-05T11:15:30.556Z
           * source : web
           * type : Android
           * url : https://github.com/shellljx/FuckingVolumeSlider
           * used : true
           * who : li jinxiang
           */

          private String _id;
          private String createdAt;
          private String desc;
          private String publishedAt;
          private String source;
          private String type;
          private String url;
          private boolean used;
          private String who;
          private String[] images;

          public String get_id() {
               return _id;
          }

          public void set_id(String _id) {
               this._id = _id;
          }

          public String getCreatedAt() {
               return createdAt;
          }

          public void setCreatedAt(String createdAt) {
               this.createdAt = createdAt;
          }

          public String getDesc() {
               return desc;
          }

          public void setDesc(String desc) {
               this.desc = desc;
          }

          public String getPublishedAt() {
               return publishedAt;
          }

          public void setPublishedAt(String publishedAt) {
               this.publishedAt = publishedAt;
          }

          public String getSource() {
               return source;
          }

          public void setSource(String source) {
               this.source = source;
          }

          public String getType() {
               return type;
          }

          public void setType(String type) {
               this.type = type;
          }

          public String getUrl() {
               return url;
          }

          public void setUrl(String url) {
               this.url = url;
          }

          public boolean isUsed() {
               return used;
          }

          public void setUsed(boolean used) {
               this.used = used;
          }

          public String getWho() {
               return who;
          }

          public void setWho(String who) {
               this.who = who;
          }

          public String[] getImages() {
               return images;
          }

          public void setImages(String[] images) {
               this.images = images;
          }
     }
}
