package com.hy.ggank;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hy.ggank.ui.about.AboutFragment;
import com.hy.ggank.base.BaseActivity;
import com.hy.ggank.ui.collection.CollectionActivity;
import com.hy.ggank.ui.data.DataTextFragment;
import com.hy.ggank.ui.program.ProgramFragment;
import com.hy.ggank.utils.ToastUtils;
import com.hy.ggank.ui.welfare.WelfareDialogFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.hy.ggank.constants.Constants.TYPE;


public class MainActivity extends BaseActivity {

     @BindView(R.id.toolbar)
     Toolbar toolbar;
     @BindView(R.id.container)
     FrameLayout container;
     @BindView(R.id.radioGroup)
     RadioGroup radioGroup;
     @BindView(R.id.recommendRadio)
     RadioButton recommendRadio;
     @BindView(R.id.programRadio)
     RadioButton programRadio;
     @BindView(R.id.loveRadio)
     Button loveRadio;
     @BindView(R.id.videoRadio)
     RadioButton videoRadio;
     @BindView(R.id.expandRadio)
     RadioButton expandRadio;
     @BindView(R.id.bottomLayout)
     RelativeLayout bottomLayout;

     @OnClick(R.id.loveRadio)
     void onClick(View view) {
          switch (view.getId()) {
               case R.id.loveRadio:
                    if (isFastDoubleClick()) {
                         ToastUtils.toast(clickTooFastTxt);
                         return;
                    }
                    if(welfareDialog!=null){
                         welfareDialog.dismiss();
                         welfareDialog = null;
                    }
                    welfareDialog = new WelfareDialogFragment();
                    welfareDialog.show(getSupportFragmentManager(), "");
                    break;
          }
     }

     @BindString(R.string.recommend_txt)
     String recommendTxt;
     @BindString(R.string.random_recommend_txt)
     String randomRecommendTxt;
     @BindString(R.string.program_txt)
     String programTxt;
     @BindString(R.string.video_txt)
     String videoTxt;
     @BindString(R.string.rest_video_txt)
     String restVideoTxt;
     @BindString(R.string.about_txt)
     String aboutTxt;
     @BindString(R.string.expand_resources_txt)
     String expandResourcesTxt;
     @BindString(R.string.click_too_fast_txt)
     String clickTooFastTxt;

     Bundle bundle;

     private String requestData;

     DataTextFragment dataRecommendFragment;
     DataTextFragment dataVideoFragment;
     AboutFragment aboutFragment;
     ProgramFragment programFragment;

     FragmentTransaction transaction;

     WelfareDialogFragment welfareDialog;

     @Override
     public int getLayoutID() {
          return R.layout.activity_main;
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          initView();
     }

     @Override
     public void initTitle() {
          toolbar.setTitle(recommendTxt);
          setSupportActionBar(toolbar);
          setImmerseLayout(toolbar);
     }

     private void initView() {

          bundle = new Bundle();
          requestData = randomRecommendTxt;
          transaction = getSupportFragmentManager().beginTransaction();
          hideFragments(transaction);
          checkRecommendFragment();
          recommendRadio.setChecked(true);

          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               public void onCheckedChanged(RadioGroup group, int checkedId) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    hideFragments(transaction);
                    switch (checkedId) {
                         case R.id.recommendRadio:
                              requestData = randomRecommendTxt;
                              toolbar.setTitle(recommendTxt);
                              checkRecommendFragment();
                              recommendRadio.setChecked(true);
                              break;
                         case R.id.programRadio:
                              requestData = programTxt;
                              toolbar.setTitle(programTxt);
                              checkProgramFragment();
                              programRadio.setChecked(true);
                              break;
                         case R.id.videoRadio:
                              requestData = restVideoTxt;
                              toolbar.setTitle(videoTxt);
                              checkVideoFragment();
                              videoRadio.setChecked(true);
                              break;
                         case R.id.expandRadio:
                              toolbar.setTitle(aboutTxt);
                              checkAboutFragment();
                              expandRadio.setChecked(true);
                              break;
                         default:
                              break;
                    }
               }
          });
     }

     private void hideFragments(FragmentTransaction fragmentTransaction) {
          if (dataRecommendFragment != null) {
               fragmentTransaction.hide(dataRecommendFragment);
          }
          if (dataVideoFragment != null) {
               fragmentTransaction.hide(dataVideoFragment);
          }
          if (aboutFragment != null) {
               fragmentTransaction.hide(aboutFragment);
          }
          if (programFragment != null) {
               fragmentTransaction.hide(programFragment);
          }
     }


     private void checkRecommendFragment() {
          bundle.putString(TYPE, requestData);
          if (dataRecommendFragment == null) {
               dataRecommendFragment = new DataTextFragment();
               transaction.add(R.id.container, dataRecommendFragment);
          } else {
               transaction.show(dataRecommendFragment);
          }
          dataRecommendFragment.setArguments(bundle);
          transaction.commit();
     }

     private void checkVideoFragment() {
          bundle.putString(TYPE, requestData);
          if (dataVideoFragment == null) {
               dataVideoFragment = new DataTextFragment();
               transaction.add(R.id.container, dataVideoFragment);
          } else {
               transaction.show(dataVideoFragment);
          }
          dataVideoFragment.setArguments(bundle);
          transaction.commit();
     }

     public void checkProgramFragment() {
          if (programFragment == null) {
               programFragment = new ProgramFragment();
               transaction.add(R.id.container, programFragment);
          } else {
               transaction.show(programFragment);
          }
          transaction.commit();
     }

     private void checkAboutFragment() {
          if (aboutFragment == null) {
               aboutFragment = new AboutFragment();
               transaction.add(R.id.container, aboutFragment);
          } else {
               transaction.show(aboutFragment);
          }
          transaction.commit();
     }

     //TODO search功能
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.main_data_menu, menu);
          return true;
     }

     /**
      * menu菜单点击操作的监听事件
      */
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {
               case R.id.collection:
                    startActivity(CollectionActivity.class);
                    break;
          }
          return super.onOptionsItemSelected(item);
     }
}
