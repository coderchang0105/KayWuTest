package com.coderchang.kaywuzhihu.task;

import android.os.AsyncTask;

import com.coderchang.kaywuzhihu.domain.News;
import com.coderchang.kaywuzhihu.adapter.NewsAdapter;
import com.coderchang.kaywuzhihu.http.Http;
import com.coderchang.kaywuzhihu.utility.Utility;

import java.util.List;

/**
 * Created by coderchang on 2016/5/27.
 */
public class LoadNewsTask extends AsyncTask<Void,Void,List<News>>{
    public static final int DATE_LATEST = 0;
    private NewsAdapter adapter;
    private OnFinishListener listener;
    private List<News> newsList;
    private int date;
    public LoadNewsTask(NewsAdapter adapter,int date) {
        this.date = date;
        this.adapter = adapter;
    }

    public LoadNewsTask(NewsAdapter adapter,OnFinishListener listener,int date) {
        super();
        this.adapter = adapter;
        this.listener = listener;
        this.date = date;
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        if (date == DATE_LATEST) {
            newsList = Utility.parseJsonToNewsList(Http.getNewsLatest());
        } else {
            newsList = Utility.parseJsonToNewsList(Http.getDateNews(date));
        }

        return newsList;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        super.onPostExecute(newses);
        if (date == DATE_LATEST) {
            adapter.refreshNewsList(newses);
        } else {
            adapter.addNewsList(newses);
        }
        if (listener != null) {
            listener.afterTaskFinish();
        }
    }

    public interface OnFinishListener{
        void afterTaskFinish();
    }

}
