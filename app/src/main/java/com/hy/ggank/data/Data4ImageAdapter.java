package com.hy.ggank.data;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


import com.hy.ggank.R;
import com.hy.ggank.base.BaseAdapter;
import com.hy.ggank.base.BaseViewHolder;
import com.hy.ggank.content.ImageContentActivity;
import com.hy.ggank.utils.ImageUtils;

import java.util.List;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.IMAGE_WELFARE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/16.
 */

public class Data4ImageAdapter extends BaseAdapter<DataResult.ResultsBean> {


     public Data4ImageAdapter(Context context, List<DataResult.ResultsBean> data) {
          super(context, data, R.layout.data_list4imgae_item);
     }

     @Override
     protected void convert(BaseViewHolder holder, final DataResult.ResultsBean item, int position) {
          if (item.getUrl() != null) {
               holder.setImageUrl(R.id.image, new ImaggLoader(new StringBuffer()
                         .append(item.getUrl()).append("?imageView2/0/w/230").toString()));
               holder.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Intent intent = new Intent(mContext, ImageContentActivity.class);
                         intent.putExtra(URL, item.getUrl());
                         intent.putExtra(IMAGE_TYPE, IMAGE_WELFARE);
                         mContext.startActivity(intent);
                    }
               });
          }
     }

     public class ImaggLoader extends BaseViewHolder.HolderImage {

          public ImaggLoader(String url) {
               super(url);
          }

          @Override
          public void loadImg(ImageView imageView, String url) {
               ImageUtils.getInstance().displayImage(mContext, url, imageView);
          }
     }
}
