package com.coderchang.kaywutest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by coderchang on 2016/5/25.
 */
public class PagerFragment extends Fragment{
    private int pageNum;
    private TextView mTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.text_view);
        Bundle bundle = getArguments();
        pageNum = bundle.getInt("pager_num");
        mTextView.setText("" + pageNum);
        return view;
    }
}
