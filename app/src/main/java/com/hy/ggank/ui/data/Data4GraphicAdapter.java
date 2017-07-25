package com.hy.ggank.ui.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hy.ggank.R;
import com.hy.ggank.base.BaseAdapter;
import com.hy.ggank.base.BaseViewHolder;
import com.hy.ggank.db.CollectionModel;
import com.hy.ggank.ui.content.DescContentActivity;
import com.hy.ggank.ui.content.ImageContentActivity;
import com.hy.ggank.utils.ImageUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/5.
 */

public class Data4GraphicAdapter extends BaseAdapter<DataResult.ResultsBean> {

     public static CollectionModel collectionModel;

     public Data4GraphicAdapter(Context context, List<DataResult.ResultsBean> data) {
          super(context, data, R.layout.data_list4text_item);
     }

     @Override
     protected void convert(final BaseViewHolder holder, final DataResult.ResultsBean item, int position) {
          String date = item.getPublishedAt().split("T")[0];//截取字符串
          holder.setText(R.id.context, item.getDesc())
                    .setText(R.id.time, new StringBuffer()
                              .append(date)
                              .append("\t\t")
                              .append("by")
                              .append("\t\t")
                              .append(item.getWho() == null ? "Unknown" : item.getWho()));
          /**
           * 判断是否显示image
           *
           * TODO 判断是否显示 gif 标签
           */
          if (item.getImages() != null) {
               holder.getView(R.id.imgLayout).setVisibility(View.VISIBLE);
               holder.setImageUrl(R.id.img, new ImageLoader(new StringBuffer()
                         .append(item.getImages()[0]).append("?imageView2/0/w/190").toString()));
//               if (item.getImages()[0].endsWith("gif")) {
//                    holder.getView(R.id.gifLayout).setVisibility(View.VISIBLE);
//               } else {
//                    holder.getView(R.id.gifLayout).setVisibility(View.GONE);
//               }
          } else {
               holder.getView(R.id.imgLayout).setVisibility(View.GONE);
          }

          if (item.getImages() != null) {
               holder.getView(R.id.img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Intent intent = new Intent(mContext, ImageContentActivity.class);
                         intent.putExtra(URL, item.getImages()[0]);
                         intent.putExtra(IMAGE_TYPE, IMAGE_OTHER);
                         mContext.startActivity(intent);
                    }
               });
          }
          holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                    collectionModel = new CollectionModel();
                    collectionModel
                              .setPublishedAt(item.getPublishedAt())
                              .setType(item.getType())
                              .setUrl(item.getUrl())
                              .setWho(item.getWho() == null ? "Unknown" : item.getWho())
                              .setDesc(item.getDesc());
                    if(item.getImages() != null){
                         collectionModel.setImage(item.getImages()[0] == null ? "" : item.getImages()[0]);
                    }else{
                         collectionModel.setImage("");
                    }

                    Intent intent = new Intent(mContext, DescContentActivity.class);
                    intent.putExtra(URL, item.getUrl());
                    intent.putExtra(TOOLTITLE, item.getDesc());
                    mContext.startActivity(intent);
               }
          });
     }

     public class ImageLoader extends BaseViewHolder.HolderImage {

          public ImageLoader(String url) {
               super(url);
          }

          @Override
          public void loadImg(ImageView imageView, String url) {
               ImageUtils.getInstance().displayImage(mContext, url, imageView);
          }
     }
}
