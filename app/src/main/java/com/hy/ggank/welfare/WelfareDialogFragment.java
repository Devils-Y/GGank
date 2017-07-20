package com.hy.ggank.welfare;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hy.ggank.R;
import com.hy.ggank.content.ImageContentActivity;
import com.hy.ggank.data.DataResult;
import com.hy.ggank.randomdata.RandomDataPresenter;
import com.hy.ggank.randomdata.RandomDataView;
import com.hy.ggank.utils.ImageUtils;
import com.hy.ggank.utils.ToastUtils;
import com.hy.ggank.widget.CustomProgressDialog;

import java.lang.ref.WeakReference;

import static com.hy.ggank.constants.Constants.CLOSE_WELFARE;
import static com.hy.ggank.constants.Constants.IMAGE_TYPE;
import static com.hy.ggank.constants.Constants.IMAGE_WELFARE;
import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/19.
 * <p>
 * 福利
 */

public class WelfareDialogFragment extends DialogFragment implements RandomDataView {

     RandomDataPresenter randomDataPresenter;

     private static final String COUNT = "1";//每页的item数

     ImageView welfareImg;

     CustomProgressDialog customProgressDialog;

     String url;

     @Override
     public void onActivityCreated(Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);
          getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          randomDataPresenter = new RandomDataPresenter(this);

          customProgressDialog = new CustomProgressDialog(getActivity());
          customProgressDialog.show();

          randomDataPresenter.getData(getString(R.string.welfare_txt), COUNT);
     }

     @Override
     public void onResume() {
          ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
          params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
          params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
          getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
          super.onResume();
     }

     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          LayoutInflater inflater = getActivity().getLayoutInflater();
          View view = inflater.inflate(R.layout.dialog_welfare, null);
          welfareImg = view.findViewById(R.id.welfareImg);
          welfareImg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    if (url != null) {
                         Intent intent = new Intent(getActivity(), ImageContentActivity.class);
                         intent.putExtra(URL, url);
                         intent.putExtra(IMAGE_TYPE, IMAGE_WELFARE);
                         startActivity(intent);
                         dismiss();
                    } else {
                         ToastUtils.toast(getString(R.string.where_welfare_go_txt));
                    }
               }
          });
          builder.setView(view);
          return builder.create();
     }

     @Override
     public void onSuccess(DataResult dataResult) {
          WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(welfareImg);
          ImageView target = imageViewWeakReference.get();
          if (dataResult.getResults()[0].getUrl() != null) {
               url = dataResult.getResults()[0].getUrl();
               ImageUtils.getInstance().displayImage(getActivity(),
                         new StringBuffer().append(dataResult.getResults()[0].getUrl())
                                   .append("?imageView2/0/w/190").toString(), target);
               if (customProgressDialog != null) {
                    customProgressDialog.dismiss();
               }
          }
     }

     @Override
     public void onError() {

     }
}
