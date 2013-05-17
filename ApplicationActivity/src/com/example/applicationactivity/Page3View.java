package com.example.applicationactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.EventListener;

/**
 * Created by jaehee.cho on 2013-05-17.
 */

public class Page3View extends RelativeLayout {
    public static abstract interface Page3Callbacks extends EventListener {
        public void onPage3BackClick(View view);
        public void onPage3DoubleBackClick(View view);
    }

    private Page3Callbacks mCallbacks;

    public Page3View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Button mBack = (Button) findViewById(R.id.button_back_2);
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallbacks != null) {
                    mCallbacks.onPage3BackClick(view);
                }
            }
        });
        Button mDoubleBack = (Button) findViewById(R.id.button_doubleback);
        mDoubleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallbacks != null) {
                    mCallbacks.onPage3DoubleBackClick(view);
                }
            }
        });
    }

    public void setPage3Callback (Page3Callbacks callbacks) {
        mCallbacks = callbacks;
    }
}
