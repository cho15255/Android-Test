package com.example.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.View.Page1VIew;
import com.example.View.Page2View;
import com.example.View.Page3View;

import java.lang.ref.WeakReference;

public class ApplicationActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, Page1VIew.Page1Callbacks,
        Page2View.Page2Callbacks, Page3View.Page3Callbacks {

    @Override
    public void onPage1BeginClick (View view) {
        mPager.setCurrentItem(1);
    }

    @Override
    public void onPage2BackClick(View view) {
        mPager.setCurrentItem(0);
    }

    @Override
    public void onPage2NextClick(View view) {
        mPager.setCurrentItem(2);
    }

    @Override
    public void onPage3BackClick(View view) {
        mPager.setCurrentItem(1);
    }

    @Override
    public void onPage3DoubleBackClick(View view) {
        mPager.setCurrentItem(0);
    }

    private static final class ApplicationActivityAdapter extends PagerAdapter {
		private static WeakReference<ApplicationActivity> mActivity;

        private static enum Views {
            PAGE_1(R.layout.page_1),
            PAGE_2(R.layout.page_2),
            PAGE_3(R.layout.page_3);

            public final int layout;

            private Views (int layout) {
                this.layout = layout;
            }
        }

		public ApplicationActivityAdapter(ApplicationActivity applicationActivity) {
			mActivity = new WeakReference<ApplicationActivity>(applicationActivity);
		}

		@SuppressWarnings("unused") public void destroy() {
			mActivity.clear();
		}

		@Override public int getCount() {
			return Views.values().length;
		}

        @Override public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override public Object instantiateItem(ViewGroup container, int position) {
			View view = null;
            if (mActivity.get() != null) {
                if (position == Views.PAGE_1.ordinal()) {
                    view = mActivity.get().getLayoutInflater().inflate(Views.PAGE_1.layout, null);
                    ((Page1VIew) view).setPage1Callbacks(mActivity.get());
                } else if (position == Views.PAGE_2.ordinal()) {
                    view = mActivity.get().getLayoutInflater().inflate(Views.PAGE_2.layout, null);
                    ((Page2View) view).setPage2Callbacks(mActivity.get());
                } else if (position == Views.PAGE_3.ordinal()) {
                    view = mActivity.get().getLayoutInflater().inflate(Views.PAGE_3.layout, null);
                    ((Page3View) view).setPage3Callback(mActivity.get());
                }
				container.addView(view);
			}
			return view;
		}
	}

    private ViewPager mPager;

    @Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_activity);
        ApplicationActivityAdapter mAdapter = new ApplicationActivityAdapter(this);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setOffscreenPageLimit(5);
		mPager.setOnPageChangeListener(this);
		mPager.setAdapter(mAdapter);
	}

    @Override public void onPageScrollStateChanged(int arg0) {

	}

	@Override public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override public void onPageSelected(int position) {
		switch (position) {
			case 0:
				Log.d("selected page", "first page");
				break;

			case 1:
				Log.d("selected page", "second page");
				break;

			case 2:
				Log.d("selected page", "third page");
		}
	}
}
