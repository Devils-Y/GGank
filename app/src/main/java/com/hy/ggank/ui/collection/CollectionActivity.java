package com.hy.ggank.ui.collection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseDividerItemDecoration;
import com.hy.ggank.base.BaseSwipeActivity;
import com.hy.ggank.db.CollectionManager;
import com.hy.ggank.db.CollectionModel;
import com.hy.ggank.utils.ToastUtils;
import com.hy.ggank.widget.MyRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

import static com.hy.ggank.constants.Constants.COLLECTION_CHANGE;
import static com.jcodecraeer.xrecyclerview.ProgressStyle.Pacman;
import static com.jcodecraeer.xrecyclerview.ProgressStyle.SquareSpin;

public class CollectionActivity extends BaseSwipeActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.collectionRecyclerView)
     MyRecyclerView collectionRecyclerView;

     @BindString(R.string.collection_txt)
     String collectionTxt;
     @BindString(R.string.delete_success_txt)
     String deleteSuccessTxt;
     @BindString(R.string.delete_error_txt)
     String deleteErrorTxt;

     List<CollectionModel> mCollectionList;
     CollectionAdapter mCollectionAdapter;
     CollectionManager mCollectionManager;
     int page = 1;

     private static final int COUNT = 10;

     LinearLayoutManager linearLayoutManager;

     @Override
     public int getLayoutID() {
          return R.layout.activity_collection;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);


          mCollectionList = new ArrayList<>();
          mCollectionAdapter = new CollectionAdapter(this, mCollectionList);
          mCollectionManager = new CollectionManager(this);
          getData();
          initView();
          receiveHaveChange();
     }

     @Override
     public void initTitle() {
          toolbar.setTitle(collectionTxt);
          setSupportActionBar(toolbar);
          toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (isFastDoubleClick()) {
                         return;
                    }
                    finish();
               }
          });
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          setImmerseLayout(toolbar);
     }

     private void initView() {

          linearLayoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false);
          collectionRecyclerView.setLayoutManager(linearLayoutManager);
          collectionRecyclerView.addItemDecoration(new BaseDividerItemDecoration(this,
                    BaseDividerItemDecoration.VERTICAL));
          ((SimpleItemAnimator) collectionRecyclerView.getItemAnimator())
                    .setSupportsChangeAnimations(false);
          collectionRecyclerView.setAdapter(mCollectionAdapter);
          collectionRecyclerView.setPullRefreshEnabled(true);
          collectionRecyclerView.setLoadingMoreEnabled(true);
          collectionRecyclerView.setRefreshProgressStyle(Pacman);
          collectionRecyclerView.setLoadingMoreProgressStyle(SquareSpin);
          collectionRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
               @Override
               public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                              page = 1;
                              mCollectionAdapter.clear();
                              getData();
                              collectionRecyclerView.setLoadingMoreEnabled(true);
                              if (collectionRecyclerView != null) {
                                   collectionRecyclerView.refreshComplete();
                              }
                         }
                    }, 1000);
               }

               @Override
               public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                         public void run() {
                              page++;
                              getData();
                              if (collectionRecyclerView != null) {
                                   collectionRecyclerView.loadMoreComplete();
                              }
                         }
                    }, 50);
               }
          });
          mCollectionAdapter.setOnButtonClick(new CollectionAdapter.OnButtonClick() {
               @Override
               public void onClick(View view, final String url, final int position) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CollectionActivity.this);
                    alertDialogBuilder.setTitle(getString(R.string.alert_title_txt));//设置标题
                    alertDialogBuilder.setMessage(getString(R.string.whether_to_delete_txt));//设置标题
                    /*设置下方按钮*/
                    alertDialogBuilder.setPositiveButton(getString(R.string.cancel_txt), null);
                    alertDialogBuilder.setNegativeButton(getString(R.string.sure_txt), new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                              boolean delete = mCollectionManager.delete(url);
                              if (delete) {
                                   ToastUtils.toast(deleteSuccessTxt);
                                   mCollectionAdapter.remove(position);
                              } else {
                                   ToastUtils.toast(deleteErrorTxt);
                              }
                         }
                    });
                    alertDialogBuilder.show();
               }
          });
     }

     private void getData() {
          mCollectionList = mCollectionManager.queryByPage(page, COUNT);
          mCollectionAdapter.add(mCollectionList);
          if (mCollectionManager.queryCollection().size() == mCollectionAdapter.getDataSize()) {
               ToastUtils.toast("已加载全部!");
               collectionRecyclerView.loadMoreComplete();
               collectionRecyclerView.setLoadingMoreEnabled(false);
          }
     }


     LocalBroadcastManager broadcastManager;

     /**
      * 注册广播接收器
      */
     private void receiveHaveChange() {
          broadcastManager = LocalBroadcastManager.getInstance(this);
          IntentFilter intentFilter = new IntentFilter();
          intentFilter.addAction(COLLECTION_CHANGE);
          broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
     }

     /**
      * 接收到删除步骤的广播并进行处理
      */
     BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
               page = 1;
               mCollectionAdapter.clear();
               getData();
          }
     };

     @Override
     public void onDestroy() {
          super.onDestroy();
          broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
     }
}
