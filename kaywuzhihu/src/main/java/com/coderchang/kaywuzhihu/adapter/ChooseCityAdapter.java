package com.coderchang.kaywuzhihu.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderchang.kaywuzhihu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderchang on 2016/6/8.
 */
public class ChooseCityAdapter extends RecyclerView.Adapter<ChooseCityAdapter.CityViewHolder> {
    private Context mContext;

    private List<String> mData = new ArrayList<>();

    private OnItemClickListener mListener;

    public ChooseCityAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityViewHolder holder = new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_choose_city, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, final int position) {
        holder.tvProvinceOrCity.setText(mData.get(position));
        //final int itemPos = holder.getLayoutPosition();
        holder.cvProvinceOrCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class CityViewHolder extends RecyclerView.ViewHolder {

        public CityViewHolder(View itemView) {
            super(itemView);
            tvProvinceOrCity = (TextView) itemView.findViewById(R.id.province_or_city);
            cvProvinceOrCity = (CardView) itemView.findViewById(R.id.cv_province_or_city);
        }

        TextView tvProvinceOrCity;
        CardView cvProvinceOrCity;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
