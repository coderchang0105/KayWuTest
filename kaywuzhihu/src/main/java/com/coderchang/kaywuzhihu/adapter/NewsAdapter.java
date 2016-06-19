package com.coderchang.kaywuzhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderchang.kaywuzhihu.domain.News;
import com.coderchang.kaywuzhihu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by coderchang on 2016/5/27.
 */
public class NewsAdapter extends ArrayAdapter<News>{

    private LayoutInflater inflater;
    private int resource;
    private Context context;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnFail(R.drawable.empty)
            .showImageOnLoading(R.drawable.empty)
            .showImageForEmptyUri(R.drawable.empty)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();

    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = View.inflate(context, resource, null);
            viewHolder = new ViewHolder();
            viewHolder.newsImageView = (ImageView) view.findViewById(R.id.iv_image);
            viewHolder.newsTextView = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.newsTextView.setText(news.getTitle());
        imageLoader.displayImage(news.getImage(), viewHolder.newsImageView, options);
        return view;
    }

    public void refreshNewsList(List<News> newsList) {
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView newsImageView;
        TextView newsTextView;
    }

    public void addNewsList(List<News> newsList) {
        addAll(newsList);
        notifyDataSetChanged();
    }


}
