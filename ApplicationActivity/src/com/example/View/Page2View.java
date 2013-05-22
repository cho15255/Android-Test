package com.example.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.Controller.R;

import java.util.EventListener;

/**
 * Created by jaehee.cho on 2013-05-17.
 */

public class Page2View extends RelativeLayout{
    public static abstract interface Page2Callbacks extends EventListener {
        public void onPage2BackClick(View view);
        public void onPage2NextClick(View view);
    }

    private Page2Callbacks mCallbacks;

    public Page2View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Button mBack = (Button) findViewById(R.id.button_back_1);
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallbacks != null) {
                    mCallbacks.onPage2BackClick(view);
                }
            }
        });

        Button mNext = (Button) findViewById(R.id.button_next);
        mNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallbacks != null) {
                    mCallbacks.onPage2NextClick(view);
                }
            }
        });
    }

    public void setPage2Callbacks(Page2Callbacks callbacks) {
        mCallbacks = callbacks;
    }
}
