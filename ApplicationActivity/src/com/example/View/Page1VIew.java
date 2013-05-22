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

public class Page1VIew extends RelativeLayout {
    public static abstract interface Page1Callbacks extends EventListener {
        public abstract void onPage1BeginClick(View view);
    }

    private Page1Callbacks mCallbacks;

    public Page1VIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Button mBegin = (Button) findViewById(R.id.button_begin);
        mBegin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallbacks != null) {
                    mCallbacks.onPage1BeginClick(view);
                }
            }
        });
    }

    public void setPage1Callbacks(Page1Callbacks callbacks) {
        mCallbacks = callbacks;
    }
}
