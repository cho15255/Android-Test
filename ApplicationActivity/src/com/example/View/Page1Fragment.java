package com.example.View;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.Controller.R;

import java.util.EventListener;

/**
 * Created by jaehee.cho on 2013-05-22.
 */
public class Page1Fragment extends Fragment{
    public static interface Page1FragmentCallback extends EventListener {
        public void onBeginClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View parent = inflater.inflate(R.layout.page_1, container, false);
        mImageView = (ImageView) parent.findViewById(R.id.image_1);
        mButtonBegin = (Button) parent.findViewById(R.id.button_begin);

        return parent;
    }

    private View mImageView;
    private Button mButtonBegin;
}
