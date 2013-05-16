package com.example.applicationactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class ApplicationActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {

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
				final ApplicationActivity activity;
                activity = mActivity.get();
                if (activity == null) throw new AssertionError();

				if (mActivity.get() != null) {
                    if (position == Views.PAGE_1.ordinal()) {
                        view = activity.getLayoutInflater().inflate(Views.PAGE_1.layout, null);
                        view.findViewById(R.id.button_begin).setOnClickListener(mActivity.get());
                    } else if (position == Views.PAGE_2.ordinal()) {
                        view = activity.getLayoutInflater().inflate(Views.PAGE_2.layout, null);
                        view.findViewById(R.id.button_back_1).setOnClickListener(mActivity.get());
                        view.findViewById(R.id.button_next).setOnClickListener(mActivity.get());
                    } else if (position == Views.PAGE_3.ordinal()) {
                        view = activity.getLayoutInflater().inflate(Views.PAGE_3.layout, null);
                        view.findViewById(R.id.button_back_2).setOnClickListener(mActivity.get());
                        view.findViewById(R.id.button_doubleback).setOnClickListener(mActivity.get());
                    }
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

	@Override public void onPageSelected(int arg0) {
		switch (arg0) {
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

	@Override public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_begin:
				mPager.setCurrentItem(1);
				break;
			case R.id.button_back_1:
				mPager.setCurrentItem(0);
				break;
			case R.id.button_next:
				mPager.setCurrentItem(2);
				break;
			case R.id.button_back_2:
				mPager.setCurrentItem(1);
				break;
			case R.id.button_doubleback:
				mPager.setCurrentItem(0);
				break;
		}

	}

}
