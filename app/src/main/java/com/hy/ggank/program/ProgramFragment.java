package com.hy.ggank.program;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseFragment;
import com.hy.ggank.data.DataTextFragment;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.TYPE;

/**
 * Created by huyin on 2017/7/18.
 */

public class ProgramFragment extends BaseFragment {

     @BindView(R.id.tab)
     TabLayout tab;
     @BindView(R.id.pager)
     ViewPager pager;

     ProgramAdapter pagerAdapter;

     @Override
     public int getLayoutId() {
          return R.layout.fragment_program;
     }

     @Override
     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          pagerAdapter = new ProgramAdapter(getChildFragmentManager());
          pager.setAdapter(pagerAdapter);
          tab.setupWithViewPager(pager);
     }


     private class ProgramAdapter extends FragmentStatePagerAdapter {

          private String tabTitles[] = new String[]{
                    getString(R.string.app_txt),
                    getString(R.string.ios_txt),
                    getString(R.string.frontend_txt),
                    getString(R.string.android_txt)};

          public ProgramAdapter(FragmentManager fm) {
               super(fm);
          }

          @Override
          public Fragment getItem(int position) {
               //初始化Fragment数据
               Bundle bundle = new Bundle();
               bundle.putString(TYPE, tabTitles[position]);
               DataTextFragment fragment = new DataTextFragment();
               fragment.setArguments(bundle);
               return fragment;
          }

          @Override
          public int getCount() {
               return tabTitles.length;
          }

          @Override
          public CharSequence getPageTitle(int position) {
               return tabTitles[position];
          }
     }

}
