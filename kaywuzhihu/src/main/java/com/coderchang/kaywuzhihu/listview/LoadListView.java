package com.coderchang.kaywuzhihu.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.coderchang.kaywuzhihu.R;

/**
 * Created by coderchang on 2016/5/30.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener{
    private View footer;
    private int totalItemCount;//总的数量
    private int lastVisibleCount;//最后一个可见的item
    private boolean isLoading;//是否已经加载
    private OnLoadListener loadListener;//加载时回调的接口
    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleCount && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                loadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleCount = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }


    public interface OnLoadListener{
        void onLoad();
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void loadComplete() {

        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }
}
