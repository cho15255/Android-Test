package com.example.applicationactivity;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ApplicationActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener{
	
	private static final class ApplicationActivityAdapter extends PagerAdapter {
		private static final int[] LAYOUT_IDS = {R.layout.page_1, R.layout.page_2, R.layout.page_3};
		private final WeakReference<ApplicationActivity> mActivity;
		
		public ApplicationActivityAdapter(
				ApplicationActivity applicationActivity) {
			mActivity = new WeakReference<ApplicationActivity>(applicationActivity);
		}
		
		@SuppressWarnings("unused")
		public void destroy() {
			mActivity.clear();
		}
	
		@Override
		public int getCount() {
			return LAYOUT_IDS.length;
		}
	
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = null;
			if (mActivity.get() != null) {
				final ApplicationActivity activity = mActivity.get();
				view = activity.getLayoutInflater().inflate(LAYOUT_IDS[position], null);
				
				if (mActivity.get() != null) {
					switch (position) {
					case 0:
						mActivity.get().mPage1 = (RelativeLayout) view;
						mActivity.get().mButtonBegin = (Button) view.findViewById(R.id.button_begin);
						mActivity.get().mButtonBegin.setOnClickListener(mActivity.get());
						break;
					case 1:
						mActivity.get().mPage2 = (RelativeLayout) view;
						mActivity.get().mButtonBack1 = (Button) view.findViewById(R.id.button_back_1);
						mActivity.get().mButtonBack1.setOnClickListener(mActivity.get());
						mActivity.get().mButtonNext = (Button) view.findViewById(R.id.button_next);
						mActivity.get().mButtonNext.setOnClickListener(mActivity.get());
						break;
					case 2:
						mActivity.get().mPage3 = (RelativeLayout) view;
						mActivity.get().mButtonBack2 = (Button) view.findViewById(R.id.button_back_2);
						mActivity.get().mButtonBack2.setOnClickListener(mActivity.get());
						mActivity.get().mButtonDoubleBack = (Button) view.findViewById(R.id.button_doubleback);
						mActivity.get().mButtonDoubleBack.setOnClickListener(mActivity.get());
						break;
					}
				}
				container.addView(view);
			}	
			return view;
		}
	}

	private ApplicationActivityAdapter mAdapter;
	private ViewPager mPager;
	private RelativeLayout mPage1;
	private RelativeLayout mPage2;
	private RelativeLayout mPage3;
	private Button mButtonBegin;
	private Button mButtonBack1;
	private Button mButtonBack2;
	private Button mButtonNext;
	private Button mButtonDoubleBack;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_activity);
        mAdapter = new ApplicationActivityAdapter(this);
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(5);
        mPager.setOnPageChangeListener(this);
        mPager.setAdapter(mAdapter);
    }
    
    @Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
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

	@Override
	public void onClick(View v) {
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
