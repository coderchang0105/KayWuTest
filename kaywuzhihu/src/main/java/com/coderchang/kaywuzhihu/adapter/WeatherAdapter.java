package com.coderchang.kaywuzhihu.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.domain.Weather;
import com.coderchang.kaywuzhihu.domain.WeatherHourlyForecast;
import com.coderchang.kaywuzhihu.utility.Utility;

import static com.coderchang.kaywuzhihu.R.id.card_view_mains;

/**
 * Created by coderchang on 2016/6/5.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_MAIN = 0;
    public static final int ITEM_CLOCK = 1;
    public static final int ITEM_SUGGESTION = 2;
    public static final int ITEM_FORECAST = 3;
    private Context mContext;

    private OnCityClickListener mListener;
    private Weather mWeather;

    public WeatherAdapter(Context context, Weather weather) {

        this.mContext = context;
        this.mWeather = weather;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_MAIN) {
            return new MainHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather_main, parent, false));
        }

        if (viewType == ITEM_CLOCK) {
            return new ClockHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather_clock, parent, false));
        }

        if (viewType == ITEM_SUGGESTION) {
            return new SuggestionHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather_suggestion, parent, false));
        }

        if (viewType == ITEM_FORECAST) {
            return new ForecastHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather_forecast, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainHolder) {
            MainHolder myHolder = (MainHolder) holder;
            myHolder.ivCond.setImageResource(Utility.getCondResource(mWeather.getCond()));
            myHolder.tvRealTimeTmp.setText(mWeather.getTmp()+"℃");
            myHolder.tvMaxTmp.setText("↑ "+mWeather.getTmpMax()+" °");
            myHolder.tvMinTmp.setText("↓ "+mWeather.getTmpMin()+" °");
            myHolder.tvPM25.setText("pm2.5:"+mWeather.getPm());
            myHolder.tvAirCondition.setText("空气质量:"+mWeather.getAirCondition());
            myHolder.tvCityName.setText(mWeather.getCityName());
            myHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick();
                }
            });
        }
        if (holder instanceof ClockHolder) {
            ClockHolder myHolder = (ClockHolder) holder;
//            myHolder.tvClock1.setText(mWeather.getHourlyForecastList().get(0).getTime());
//            myHolder.tvClock2.setText(mWeather.getHourlyForecastList().get(1).getTime());
//            myHolder.tvClock3.setText(mWeather.getHourlyForecastList().get(2).getTime());
//            myHolder.tvClock4.setText(mWeather.getHourlyForecastList().get(3).getTime());
//
//            myHolder.tvClock1Tmp.setText(mWeather.getHourlyForecastList().get(0).getTmp());
//            myHolder.tvClock2Tmp.setText(mWeather.getHourlyForecastList().get(1).getTmp());
//            myHolder.tvClock3Tmp.setText(mWeather.getHourlyForecastList().get(2).getTmp());
//            myHolder.tvClock4Tmp.setText(mWeather.getHourlyForecastList().get(3).getTmp());
//
//            myHolder.tvClock1Humidity.setText(mWeather.getHourlyForecastList().get(0).getHumidity());
//            myHolder.tvClock2Humidity.setText(mWeather.getHourlyForecastList().get(1).getHumidity());
//            myHolder.tvClock3Humidity.setText(mWeather.getHourlyForecastList().get(2).getHumidity());
//            myHolder.tvClock4Humidity.setText(mWeather.getHourlyForecastList().get(3).getHumidity());
//
//            myHolder.tvClock1WindSpeed.setText(mWeather.getHourlyForecastList().get(0).getWind().getSpeed());
//            myHolder.tvClock2WindSpeed.setText(mWeather.getHourlyForecastList().get(1).getWind().getSpeed());
//            myHolder.tvClock3WindSpeed.setText(mWeather.getHourlyForecastList().get(2).getWind().getSpeed());
//            myHolder.tvClock4WindSpeed.setText(mWeather.getHourlyForecastList().get(3).getWind().getSpeed());


            for (int i = 0; i < mWeather.getHourlyForecastList().size(); i++) {
                WeatherHourlyForecast weatherHourlyForecast = mWeather.getHourlyForecastList().get(i);
                myHolder.tvClockList[i].setText(Utility.subClock(weatherHourlyForecast.getTime()));
                myHolder.tvTmpList[i].setText(weatherHourlyForecast.getTmp()+"°");
                myHolder.tvHumidityList[i].setText(weatherHourlyForecast.getHumidity()+"%");
                myHolder.tvWindSpeedList[i].setText(weatherHourlyForecast.getWind().getSpeed());
            }

        }
        if (holder instanceof SuggestionHolder) {
            SuggestionHolder myHolder = (SuggestionHolder) holder;
            myHolder.tvCloth.setText("穿衣指数--" + mWeather.getSuggestion().getCloth());
            myHolder.tvClothSuggestion.setText(mWeather.getSuggestion().getClothSuggestion());
            myHolder.tvSport.setText("运动指数--" + mWeather.getSuggestion().getSport());
            myHolder.tvSportSuggestion.setText(mWeather.getSuggestion().getSportSuggestion());
            myHolder.tvTravel.setText("旅游指数--" + mWeather.getSuggestion().getTravel());
            myHolder.tvTravelSuggestion.setText(mWeather.getSuggestion().getTravelSuggestion());
            myHolder.tvFlu.setText("感冒指数--" + mWeather.getSuggestion().getFlu());
            myHolder.tvFluSuggestion.setText(mWeather.getSuggestion().getFluSuggestion());
        }
        if (holder instanceof ForecastHolder) {
            ForecastHolder myHolder = (ForecastHolder) holder;
            myHolder.ivCondDay1.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(0).getCodeD()));
            myHolder.ivCondDay2.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(1).getCodeD()));
            myHolder.ivCondDay3.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(2).getCodeD()));
            myHolder.ivCondDay4.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(3).getCodeD()));
            myHolder.ivCondDay5.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(4).getCodeD()));
            myHolder.ivCondDay6.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(5).getCodeD()));
            myHolder.ivCondDay7.setImageResource(Utility.getCondResource(mWeather.getDailyForecastList().get(6).getCodeD()));

            myHolder.tvDay1.setText("今天");
            myHolder.tvDay2.setText("明天");
            myHolder.tvDay3.setText(mWeather.getDailyForecastList().get(2).getDate());
            myHolder.tvDay4.setText(mWeather.getDailyForecastList().get(3).getDate());
            myHolder.tvDay5.setText(mWeather.getDailyForecastList().get(4).getDate());
            myHolder.tvDay6.setText(mWeather.getDailyForecastList().get(5).getDate());
            myHolder.tvDay7.setText(mWeather.getDailyForecastList().get(6).getDate());

            myHolder.tvDay1Tmp.setText(mWeather.getDailyForecastList().get(0).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(0).getTmpMax()+"°");
            myHolder.tvDay2Tmp.setText(mWeather.getDailyForecastList().get(1).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(1).getTmpMax()+"°");
            myHolder.tvDay3Tmp.setText(mWeather.getDailyForecastList().get(2).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(2).getTmpMax()+"°");
            myHolder.tvDay4Tmp.setText(mWeather.getDailyForecastList().get(3).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(3).getTmpMax()+"°");
            myHolder.tvDay5Tmp.setText(mWeather.getDailyForecastList().get(4).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(4).getTmpMax()+"°");
            myHolder.tvDay6Tmp.setText(mWeather.getDailyForecastList().get(5).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(5).getTmpMax()+"°");
            myHolder.tvDay7Tmp.setText(mWeather.getDailyForecastList().get(6).getTmpMin()+"°"+" "+mWeather.getDailyForecastList().get(6).getTmpMax()+"°");

            myHolder.tvDay1Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(0)));
            myHolder.tvDay2Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(1)));
            myHolder.tvDay3Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(2)));
            myHolder.tvDay4Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(3)));
            myHolder.tvDay5Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(4)));
            myHolder.tvDay6Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(5)));
            myHolder.tvDay7Detail.setText(Utility.getWeatherDetail(mWeather.getDailyForecastList().get(6)));

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_MAIN;
        } else if (position == 1) {
            return ITEM_CLOCK;
        } else if (position == 2) {
            return ITEM_SUGGESTION;
        } else if (position == 3) {
            return ITEM_FORECAST;
        }

        return super.getItemViewType(position);
    }

    class MainHolder extends RecyclerView.ViewHolder {
        ImageView ivCond;
        TextView tvRealTimeTmp;
        TextView tvMaxTmp;
        TextView tvMinTmp;
        TextView tvPM25;
        TextView tvAirCondition;
        CardView cardView;
        TextView tvCityName;

        public MainHolder(View itemView) {
            super(itemView);

            ivCond = (ImageView) itemView.findViewById(R.id.iv_cond);
            tvRealTimeTmp = (TextView) itemView.findViewById(R.id.tv_real_time_tmp);
            tvMaxTmp = (TextView) itemView.findViewById(R.id.tv_max_tmp);
            tvMinTmp = (TextView) itemView.findViewById(R.id.tv_min_tmp);
            tvPM25 = (TextView) itemView.findViewById(R.id.tv_pm);
            tvAirCondition = (TextView) itemView.findViewById(R.id.tv_air_condition);
            tvCityName = (TextView) itemView.findViewById(R.id.tv_city);
            cardView = (CardView) itemView.findViewById(card_view_mains);
        }
    }

    class ClockHolder extends RecyclerView.ViewHolder {
        LinearLayout clockLinearLayout;
        TextView[] tvClockList = new TextView[mWeather.getHourlyForecastList().size()];
        TextView[] tvTmpList = new TextView[mWeather.getHourlyForecastList().size()];
        TextView[] tvHumidityList = new TextView[mWeather.getHourlyForecastList().size()];
        TextView[] tvWindSpeedList = new TextView[mWeather.getHourlyForecastList().size()];
//        TextView tvClock1;
//        TextView tvClock1Tmp;
//        TextView tvClock1Humidity;
//        TextView tvClock1WindSpeed;

//        TextView tvClock2;
//        TextView tvClock2Tmp;
//        TextView tvClock2Humidity;
//        TextView tvClock2WindSpeed;
//
//        TextView tvClock3;
//        TextView tvClock3Tmp;
//        TextView tvClock3Humidity;
//        TextView tvClock3WindSpeed;
//
//        TextView tvClock4;
//        TextView tvClock4Tmp;
//        TextView tvClock4Humidity;
//        TextView tvClock4WindSpeed;

        public ClockHolder(View itemView) {
            super(itemView);
//            tvClock1 = (TextView) itemView.findViewById(R.id.tv_clock1);
//            tvClock1Tmp = (TextView) itemView.findViewById(R.id.tv_clock1_tmp);
//            tvClock1Humidity = (TextView) itemView.findViewById(R.id.tv_clock1_humidity);
//            tvClock1WindSpeed = (TextView) itemView.findViewById(R.id.tv_clock1_wind_speed);

//            tvClock2 = (TextView) itemView.findViewById(R.id.tv_clock2);
//            tvClock2Tmp = (TextView) itemView.findViewById(R.id.tv_clock2_tmp);
//            tvClock2Humidity = (TextView) itemView.findViewById(R.id.tv_clock2_humidity);
//            tvClock2WindSpeed = (TextView) itemView.findViewById(R.id.tv_clock2_wind_speed);
//
//            tvClock3 = (TextView) itemView.findViewById(R.id.tv_clock3);
//            tvClock3Tmp = (TextView) itemView.findViewById(R.id.tv_clock3_tmp);
//            tvClock3Humidity = (TextView) itemView.findViewById(R.id.tv_clock3_humidity);
//            tvClock3WindSpeed = (TextView) itemView.findViewById(R.id.tv_clock3_wind_speed);
//
//            tvClock4 = (TextView) itemView.findViewById(R.id.tv_clock4);
//            tvClock4Tmp = (TextView) itemView.findViewById(R.id.tv_clock4_tmp);
//            tvClock4Humidity = (TextView) itemView.findViewById(R.id.tv_clock4_humidity);
//            tvClock4WindSpeed = (TextView) itemView.findViewById(R.id.tv_clock4_wind_speed);
            clockLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_clock);
            for (int i = 0; i < mWeather.getHourlyForecastList().size(); i++) {
                View view = View.inflate(mContext,R.layout.item_weather_clock_line,null);
                tvClockList[i] = (TextView) view.findViewById(R.id.tv_clock1);
                tvTmpList[i] = (TextView) view.findViewById(R.id.tv_clock1_tmp);
                tvHumidityList[i] = (TextView) view.findViewById(R.id.tv_clock1_humidity);
                tvWindSpeedList[i] = (TextView) view.findViewById(R.id.tv_clock1_wind_speed);

                if (view != null) {
                    clockLinearLayout.addView(view);
                }
            }
        }
    }

    class SuggestionHolder extends RecyclerView.ViewHolder {

        TextView tvCloth;
        TextView tvClothSuggestion;
        TextView tvSport;
        TextView tvSportSuggestion;
        TextView tvTravel;
        TextView tvTravelSuggestion;
        TextView tvFlu;
        TextView tvFluSuggestion;

        public SuggestionHolder(View itemView) {
            super(itemView);

            tvCloth = (TextView) itemView.findViewById(R.id.tv_cloth);
            tvClothSuggestion = (TextView) itemView.findViewById(R.id.tv_cloth_suggestion);

            tvSport = (TextView) itemView.findViewById(R.id.tv_sport);
            tvSportSuggestion = (TextView) itemView.findViewById(R.id.tv_sport_suggestion);

            tvTravel = (TextView) itemView.findViewById(R.id.tv_travel);
            tvTravelSuggestion = (TextView) itemView.findViewById(R.id.tv_travel_suggestion);

            tvFlu = (TextView) itemView.findViewById(R.id.tv_flu);
            tvFluSuggestion = (TextView) itemView.findViewById(R.id.tv_flu_suggestion);
        }
    }

    class ForecastHolder extends RecyclerView.ViewHolder {
        ImageView ivCondDay1;
        TextView tvDay1;
        TextView tvDay1Tmp;
        TextView tvDay1Detail;

        ImageView ivCondDay2;
        TextView tvDay2;
        TextView tvDay2Tmp;
        TextView tvDay2Detail;

        ImageView ivCondDay3;
        TextView tvDay3;
        TextView tvDay3Tmp;
        TextView tvDay3Detail;

        ImageView ivCondDay4;
        TextView tvDay4;
        TextView tvDay4Tmp;
        TextView tvDay4Detail;

        ImageView ivCondDay5;
        TextView tvDay5;
        TextView tvDay5Tmp;
        TextView tvDay5Detail;

        ImageView ivCondDay6;
        TextView tvDay6;
        TextView tvDay6Tmp;
        TextView tvDay6Detail;

        ImageView ivCondDay7;
        TextView tvDay7;
        TextView tvDay7Tmp;
        TextView tvDay7Detail;

        public ForecastHolder(View itemView) {
            super(itemView);

            ivCondDay1 = (ImageView) itemView.findViewById(R.id.iv_cond_day1);
            tvDay1 = (TextView) itemView.findViewById(R.id.tv_day1);
            tvDay1Tmp = (TextView) itemView.findViewById(R.id.tv_day1_tmp);
            tvDay1Detail = (TextView) itemView.findViewById(R.id.tv_day1_detail);

            ivCondDay2 = (ImageView) itemView.findViewById(R.id.iv_cond_day2);
            tvDay2 = (TextView) itemView.findViewById(R.id.tv_day2);
            tvDay2Tmp = (TextView) itemView.findViewById(R.id.tv_day2_tmp);
            tvDay2Detail = (TextView) itemView.findViewById(R.id.tv_day2_detail);

            ivCondDay3 = (ImageView) itemView.findViewById(R.id.iv_cond_day3);
            tvDay3 = (TextView) itemView.findViewById(R.id.tv_day3);
            tvDay3Tmp = (TextView) itemView.findViewById(R.id.tv_day3_tmp);
            tvDay3Detail = (TextView) itemView.findViewById(R.id.tv_day3_detail);

            ivCondDay4 = (ImageView) itemView.findViewById(R.id.iv_cond_day4);
            tvDay4 = (TextView) itemView.findViewById(R.id.tv_day4);
            tvDay4Tmp = (TextView) itemView.findViewById(R.id.tv_day4_tmp);
            tvDay4Detail = (TextView) itemView.findViewById(R.id.tv_day4_detail);

            ivCondDay5 = (ImageView) itemView.findViewById(R.id.iv_cond_day5);
            tvDay5 = (TextView) itemView.findViewById(R.id.tv_day5);
            tvDay5Tmp = (TextView) itemView.findViewById(R.id.tv_day5_tmp);
            tvDay5Detail = (TextView) itemView.findViewById(R.id.tv_day5_detail);

            ivCondDay6 = (ImageView) itemView.findViewById(R.id.iv_cond_day6);
            tvDay6 = (TextView) itemView.findViewById(R.id.tv_day6);
            tvDay6Tmp = (TextView) itemView.findViewById(R.id.tv_day6_tmp);
            tvDay6Detail = (TextView) itemView.findViewById(R.id.tv_day6_detail);

            ivCondDay7 = (ImageView) itemView.findViewById(R.id.iv_cond_day7);
            tvDay7 = (TextView) itemView.findViewById(R.id.tv_day7);
            tvDay7Tmp = (TextView) itemView.findViewById(R.id.tv_day7_tmp);
            tvDay7Detail = (TextView) itemView.findViewById(R.id.tv_day7_detail);
        }
    }

    /**
     * 选择城市的接口回调函数
     */

    public interface OnCityClickListener {
        void onClick();
    }

    public void setOnCityClickListener(OnCityClickListener listener) {
        this.mListener = listener;
    }
}
