package com.hy.ggank.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hy.ggank.R;
import com.hy.ggank.base.BaseFragment;
import com.hy.ggank.utils.VersionManagementUtil;

import butterknife.BindView;

import static com.hy.ggank.constants.Constants.URL;

/**
 * Created by huyin on 2017/7/21.
 * <p>
 * 关于
 */

public class AboutFragment extends BaseFragment {

     @BindView(R.id.aboutAPP)
     TextView aboutAPP;
     @BindView(R.id.version)
     TextView version;
     @BindView(R.id.viewPager)
     ViewPager viewPager;

     PageAdapter pageAdapter;

     @Override
     public int getLayoutId() {
          return R.layout.fragment_about;
     }

     @Override
     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          pageAdapter = new PageAdapter(getChildFragmentManager());
          viewPager.setAdapter(pageAdapter);

          version.setText(getString(R.string.now_version_txt) +
                    VersionManagementUtil.getVersion(getActivity()));
     }

     class PageAdapter extends FragmentPagerAdapter {

          private String url[] = new String[]{
                    getString(R.string.author_http_address),
                    getString(R.string.api_http_address)
          };

          public PageAdapter(FragmentManager fm) {
               super(fm);
          }

          @Override
          public Fragment getItem(int position) {
               Bundle bundle = new Bundle();
               bundle.putString(URL, url[position]);
               WebFragment webFragment = new WebFragment();
               webFragment.setArguments(bundle);
               return webFragment;
          }

          @Override
          public int getCount() {
               return url.length;
          }

          @Override
          public void destroyItem(ViewGroup container, int position, Object object) {
               super.destroyItem(container, position, object);
               container.removeView((View) object);
          }
     }
}
