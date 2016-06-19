package com.coderchang.kaywuzhihu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by coderchang on 2016/6/15.
 */
public class NetWorkStateReceiver extends BroadcastReceiver{

    ConnectivityManager connectivityManager;
    NetworkInfo info;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                Toast.makeText(context, "有网络" + info.getTypeName(), Toast.LENGTH_SHORT).show();
                Log.d("NetWorkStateReceiver", "有网络");
            } else {
                Toast.makeText(context, "无网络" + info.getTypeName(), Toast.LENGTH_SHORT).show();
                Log.d("NetWorkStateReceiver", "无网络");
            }
        }
    }
}
