package com.coderchang.kaywuzhihu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.adapter.NewsAdapter;
import com.coderchang.kaywuzhihu.listview.LoadListView;
import com.coderchang.kaywuzhihu.task.LoadNewsTask;
import com.coderchang.kaywuzhihu.utility.Utility;

/**
 * Created by coderchang on 2016/5/30.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private LoadListView latestListView;
    private SwipeRefreshLayout refreshLayout;
    private NewsAdapter adapter;
    private int today = 20160531;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    refreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        latestListView = (LoadListView) view.findViewById(R.id.news_list_view);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        initRefreshLayout();
        adapter = new NewsAdapter(getActivity(), R.layout.item_list_view);
        latestListView.setAdapter(adapter);
        latestListView.setOnLoadListener(new LoadListView.OnLoadListener() {
            @Override
            public void onLoad() {
                //显示有正在加载的效果故意延迟2秒
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new LoadNewsTask(adapter, today--).execute();
                        latestListView.loadComplete();
                    }
                }, 2000);

            }
        });
        new LoadNewsTask(adapter, new LoadNewsTask.OnFinishListener() {
            @Override
            public void afterTaskFinish() {
                Toast.makeText(getActivity(), "更新完成", Toast.LENGTH_SHORT).show();
            }
        }, LoadNewsTask.DATE_LATEST).execute();
        return view;
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new LoadNewsTask(adapter, LoadNewsTask.DATE_LATEST).execute();
        handler.sendEmptyMessage(1);
    }
}
