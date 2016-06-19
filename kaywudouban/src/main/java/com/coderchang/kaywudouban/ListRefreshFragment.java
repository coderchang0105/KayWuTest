package com.coderchang.kaywudouban;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderchang on 2016/5/25.
 */
public class ListRefreshFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView mListView;
    private SwipeRefreshLayout mSwipeLayout;
    private List<Fruit> fruitList;
    private MyAdapter adapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_refresh_layout, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeLayout.setOnRefreshListener(this);
        initFruit();
        adapter = new MyAdapter(getActivity(), R.layout.item_list_view, fruitList);
        mListView.setAdapter(adapter);

        //initRefresh();
        return view;
    }

    private void initRefresh() {
        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    private void initFruit() {
        fruitList = new ArrayList<>();
        Fruit apple = new Fruit(R.drawable.apple_pic, "apple");
        Fruit banana = new Fruit(R.drawable.banana_pic, "banana");
        Fruit cherry = new Fruit(R.drawable.cherry_pic, "cherry");
        Fruit grape = new Fruit(R.drawable.grape_pic, "grape");
        Fruit mango = new Fruit(R.drawable.mango_pic, "mango");
        Fruit orange = new Fruit(R.drawable.orange_pic, "orange");
        Fruit pear = new Fruit(R.drawable.pear_pic, "pear");
        Fruit pineapple = new Fruit(R.drawable.pineapple_pic, "pineapple");
        Fruit strawberry = new Fruit(R.drawable.strawberry_pic, "strawberry");
        Fruit watermelon = new Fruit(R.drawable.watermelon_pic, "watermelon");
        fruitList.add(apple);
        fruitList.add(banana);
        fruitList.add(cherry);
        fruitList.add(grape);
        fruitList.add(mango);
        fruitList.add(orange);
        fruitList.add(pear);
        fruitList.add(pineapple);
        fruitList.add(strawberry);
        fruitList.add(watermelon);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fruitList.add(new Fruit(R.drawable.apple_pic, "apple"));
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    private class MyAdapter extends ArrayAdapter<Fruit> {

        private int resourceId;

        public MyAdapter(Context context, int resource, List<Fruit> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Fruit fruit = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            TextView textView = (TextView) view.findViewById(R.id.text_view);

            textView.setText(fruit.getFruitName());
            imageView.setImageResource(fruit.getFruitResourceId());
            return view;
        }
    }
}
