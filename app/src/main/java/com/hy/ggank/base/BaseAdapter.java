package com.hy.ggank.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huyin on 2017/7/14.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
     //布局id
     private int mLayoutId;

     private List<T> mData;

     protected LayoutInflater mLayoutInflater;

     protected Context mContext;

     public BaseAdapter(Context context, List<T> data, int layoutId) {
          mLayoutInflater = LayoutInflater.from(context);
          this.mContext = context;
          this.mData = data;
          this.mLayoutId = layoutId;
     }

     @Override
     public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = mLayoutInflater.inflate(mLayoutId, parent, false);

          return new BaseViewHolder(view);
     }

     @Override
     public void onBindViewHolder(BaseViewHolder holder, int position) {
          convert(holder, mData.get(position), position);
     }

     protected abstract void convert(BaseViewHolder holder, T item, int position);

     @Override
     public int getItemCount() {
          return mData.size();
     }

     public void add(T[] t) {
          for (int i = 0; i < t.length; i++) {
               mData.add(t[i]);
          }
          notifyDataSetChanged();
     }

     public void clear() {
          mData.clear();
     }
}
