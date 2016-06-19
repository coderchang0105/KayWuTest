package com.coderchang.kaywuzhihu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.coderchang.kaywuzhihu.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by coderchang on 2016/6/3.
 */
public class DBManager {

    public static final String DB_NAME = "china_city.db";
    public static final String PACKAGE_NAME = "com.coderchang.kaywuzhihu";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath()
            + "/" + PACKAGE_NAME;
    private SQLiteDatabase mWeatherDB;
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase getWeatherDatabase() {
        mWeatherDB = readRawDB(DB_PATH + "/" + DB_NAME);//复制后的db存放的路径
        return mWeatherDB;
    }

    /**
     * 先将raw中的文件写入到dbfile中，如果已经写过了则不写，没写过就写一下
     * 然后通过SQLiteDatabase.openOrCreateDatabase的方法得到SQLiteDatabase的一个实例
     * @param dbFile
     * @return
     */
    public SQLiteDatabase readRawDB(String dbFile) {
        if (!new File(dbFile).exists()) {
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.china_city);
            try {
                FileOutputStream outputStream = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = inputStream.read(buffer))> 0) {
                    outputStream.write(buffer,0,count);
                }
                outputStream.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        return db;
    }
}
