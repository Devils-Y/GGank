package com.hy.ggank.ui.collection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseAdapter;
import com.hy.ggank.base.BaseViewHolder;
import com.hy.ggank.db.CollectionManager;
import com.hy.ggank.db.CollectionModel;
import com.hy.ggank.ui.content.DescContentActivity;
import com.hy.ggank.ui.content.ImageContentActivity;
import com.hy.ggank.utils.ImageUtils;
import com.hy.ggank.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/21.
 */

public class CollectionAdapter extends BaseAdapter<CollectionModel> {

     public static CollectionModel collectionModel;

     public CollectionAdapter(Context context, List<CollectionModel> data) {
          super(context, data, R.layout.data_list4text_item);
     }

     @Override
     protected void convert(BaseViewHolder holder, final CollectionModel item, final int position) {
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
          if (!"".equals(item.getImage())) {
               holder.getView(R.id.imgLayout).setVisibility(View.VISIBLE);
               holder.setImageUrl(R.id.img, new ImageLoader(new StringBuffer()
                         .append(item.getImage()).append("?imageView2/0/w/190").toString()));
//               if (item.getImage().endsWith("gif")) {
//                    holder.getView(R.id.gifLayout).setVisibility(View.VISIBLE);
//               } else {
//                    holder.getView(R.id.gifLayout).setVisibility(View.GONE);
//               }
          } else {
               holder.getView(R.id.imgLayout).setVisibility(View.GONE);
          }

          if (item.getImage() != null) {
               holder.getView(R.id.img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Intent intent = new Intent(mContext, ImageContentActivity.class);
                         intent.putExtra(URL, item.getImage());
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
                              .setDesc(item.getDesc())
                              .setImage(item.getImage() == null ? "" : item.getImage());

                    Intent intent = new Intent(mContext, DescContentActivity.class);
                    intent.putExtra(URL, item.getUrl());
                    intent.putExtra(TOOLTITLE, item.getDesc());
                    mContext.startActivity(intent);
               }
          });
          holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(final View view) {
                    if (onButtonClick != null) {
                         onButtonClick.onClick(view, item.getUrl(), position);
                    }
                    return true;
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

     public int getDataSize() {
          return mData.size();
     }

     public void add(List<CollectionModel> cList) {
          for (int i = 0; i < cList.size(); i++) {
               mData.add(cList.get(i));
          }
          notifyDataSetChanged();
     }

     /**
      * 移除某项
      *
      * @param position
      */
     public void remove(int position){
          mData.remove(position);
          notifyDataSetChanged();
     }

     private OnButtonClick onButtonClick;

     public OnButtonClick getOnButtonClick() {
          return onButtonClick;
     }

     public void setOnButtonClick(OnButtonClick onButtonClick) {
          this.onButtonClick = onButtonClick;
     }

     public interface OnButtonClick {
          void onClick(View view, String url,int position);
     }
}
