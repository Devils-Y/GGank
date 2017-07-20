package com.hy.ggank.data;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;


import com.hy.ggank.R;
import com.hy.ggank.base.BaseAdapter;
import com.hy.ggank.base.BaseViewHolder;
import com.hy.ggank.content.DescContentActivity;
import com.hy.ggank.content.ImageContentActivity;
import com.hy.ggank.utils.ImageUtils;

import java.util.List;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/5.
 */

public class Data4GraphicAdapter extends BaseAdapter<DataResult.ResultsBean> {

     public Data4GraphicAdapter(Context context, List<DataResult.ResultsBean> data) {
          super(context, data, R.layout.data_list4text_item);
     }

     @Override
     protected void convert(final BaseViewHolder holder, final DataResult.ResultsBean item, int position) {

          String date = item.getCreatedAt().split("T")[0];//截取字符串
          holder.setText(R.id.context, item.getDesc())
                    .setText(R.id.time, new StringBuffer()
                              .append(date)
                              .append("\t\t")
                              .append("by")
                              .append("\t\t")
                              .append(item.getWho() == null ? "Unknown" : item.getWho()));
          if (item.getImages() != null) {
               holder.getView(R.id.img).setVisibility(View.VISIBLE);
               holder.setImageUrl(R.id.img, new ImageLoader(new StringBuffer()
                         .append(item.getImages()[0]).append("?imageView2/0/w/190").toString()));
          } else {
               holder.getView(R.id.img).setVisibility(View.GONE);
          }
          holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent intent = new Intent(mContext, DescContentActivity.class);
                    intent.putExtra(URL, item.getUrl());
                    intent.putExtra(TOOLTITLE, item.getDesc());
                    mContext.startActivity(intent);
               }
          });
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
