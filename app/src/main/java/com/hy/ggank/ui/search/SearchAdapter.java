package com.hy.ggank.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseAdapter;
import com.hy.ggank.base.BaseViewHolder;
import com.hy.ggank.db.CollectionModel;
import com.hy.ggank.ui.content.DescContentActivity;
import com.hy.ggank.ui.content.ImageContentActivity;
import com.hy.ggank.ui.data.Data4GraphicAdapter;
import com.hy.ggank.utils.ImageUtils;

import java.util.List;

import static com.hy.ggank.constants.Constants.IMAGE_OTHER;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.TOOLTITLE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/24.
 */

public class SearchAdapter extends BaseAdapter<SearchResult.ResultsBean>{

     CollectionModel collectionModel;

     public SearchAdapter(Context context, List<SearchResult.ResultsBean> data) {
          super(context, data, R.layout.data_list4text_item);
     }

     @Override
     protected void convert(BaseViewHolder holder, final SearchResult.ResultsBean item, int position) {
          String date = item.getPublishedAt().split("T")[0];//截取字符串
          holder.setText(R.id.context, item.getDesc())
                    .setText(R.id.time, new StringBuffer()
                              .append(date)
                              .append("\t\t")
                              .append("by")
                              .append("\t\t")
                              .append(item.getWho() == null ? "Unknown" : item.getWho()));
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
                              .setImage("");
                    Intent intent = new Intent(mContext, DescContentActivity.class);
                    intent.putExtra(URL, item.getUrl());
                    intent.putExtra(TOOLTITLE, item.getDesc());
                    mContext.startActivity(intent);

               }
          });
     }
}
