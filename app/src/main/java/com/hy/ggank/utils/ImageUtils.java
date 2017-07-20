package com.hy.ggank.utils;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hy.ggank.R;

import java.io.File;

public class ImageUtils {

     public static final String ANDROID_RESOURCE = "android.resource://";
     public static final String FOREWARD_SLASH = "/";

     private static class ImageUtilsHolder {
          private static final ImageUtils INSTANCE = new ImageUtils();

     }

     private ImageUtils() {
     }

     public static final ImageUtils getInstance() {
          return ImageUtilsHolder.INSTANCE;
     }

     //加载网络图片和设置默认图片
     public void displayImage(Context context, String url, ImageView imageView, int resId) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .placeholder(resId)
                    .centerCrop()
                    .dontAnimate()
                    .into(imageView);
     }

     //加载drawable图片
     public void displayImage(Context context, int resId, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(resId)
                    .centerCrop()
                    .placeholder(R.mipmap.empty_photo)
                    .into(imageView);
     }

     //直接加载网络图片
     public void displayImage(Context context, String url, final ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(R.mipmap.empty_photo)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .into(imageView);
     }


     //加载SD卡图片
     public void displayImage(Context context, File file, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(file)
                    .placeholder(R.mipmap.empty_photo)
                    .centerCrop()
                    .into(imageView);

     }

     //加载SD卡图片并设置大小
     public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(file)
                    .placeholder(R.mipmap.empty_photo)
                    .override(width, height)
                    .centerCrop()
                    .into(imageView);

     }

     //加载网络图片并设置大小
     public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.empty_photo)
                    .override(width, height)
                    .into(imageView);
     }

     /**
      * 图片自动适应宽度
      *
      * @param context
      * @param url
      * @param imageView
      */
     public void displayImageUseFitWidth(Context context, String url, final ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                         @Override
                         public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                              return false;
                         }

                         @Override
                         public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                              if (imageView == null) {
                                   return false;
                              }
                              if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                   imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                              }
                              ViewGroup.LayoutParams params = imageView.getLayoutParams();
                              int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                              float scale = (float) vw / (float) resource.getIntrinsicWidth();
                              int vh = Math.round(resource.getIntrinsicHeight() * scale);
                              params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                              imageView.setLayoutParams(params);
                              return false;
                         }
                    })
                    .placeholder(R.mipmap.empty_photo)
                    .into(imageView);
     }

     //加载drawable图片显示为圆形图片
     public void displayCricleImage(Context context, int resId, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(resourceIdToUri(context, resId))
                    .crossFade()
                    .transform(new GlideCircleTransformUtil(context))
                    .placeholder(R.mipmap.empty_photo)
                    .into(imageView);
     }

     //加载网络图片显示为圆形图片
     public void displayCricleImage(Context context, String url, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .placeholder(R.mipmap.empty_photo)
                    .centerCrop()
                    .transform(new GlideCircleTransformUtil(context))
                    .dontAnimate()
                    .into(imageView);
     }

     //加载网络图片显示为自定义圆角图片
     public void displayRoundImage(Context context, String url, ImageView imageView, int round, int margin, GlideRoundTransformUtil.CornerType cornerType) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .bitmapTransform(new GlideRoundTransformUtil(context, round, margin, cornerType))
                    .placeholder(R.mipmap.empty_photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(imageView);
     }

     //加载网络图片显示为自定义圆角图片
     public void displayRoundImage(Context context, int resId, ImageView imageView, int round, int margin, GlideRoundTransformUtil.CornerType cornerType) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(resourceIdToUri(context, resId))
                    .centerCrop()
                    .bitmapTransform(new GlideRoundTransformUtil(context, round, margin, cornerType))
                    .placeholder(R.mipmap.empty_photo)
                    .into(imageView);
     }

     //加载drawable图片并设置大小
     public void displayImage(Context context, int resId, ImageView imageView, int width, int height) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(resId)
                    .crossFade()
                    .override(width, height)
                    .placeholder(R.mipmap.empty_photo)
                    .into(imageView);
     }

     /**
      * 加载网络图片并模糊处理
      *
      * @param context
      * @param url
      * @param radius
      * @param imageView
      */
     public void displayImage(Context context, String url, float radius, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .bitmapTransform(new GlideBlurTransformationUtil(context, radius))
                    .placeholder(R.mipmap.empty_photo)
                    .dontAnimate()
                    .into(imageView);
     }

     /**
      * 加载本地图片并模糊处理
      *
      * @param context
      * @param resId
      * @param radius
      * @param imageView
      */
     public void displayImage(Context context, int resId, float radius, ImageView imageView) {
          Glide.get(context).clearMemory();
          Glide.with(context)
                    .load(resId)
                    .centerCrop()
                    .bitmapTransform(new GlideBlurTransformationUtil(context, radius))
                    .placeholder(R.mipmap.empty_photo)
                    .dontAnimate()
                    .into(imageView);
     }

     //将资源ID转为Uri
     public Uri resourceIdToUri(Context context, int resourceId) {
          return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
     }

     /**
      * 停止加载图片，保证操作界面流畅
      *
      * @param context
      */
     public void pauseRequests(Context context) {
          Glide.with(context).pauseRequests();
     }

     /**
      * 静止状态时，继续加载图片
      *
      * @param context
      */
     public void resumeRequests(Context context) {
          Glide.with(context).resumeRequests();
     }
}
