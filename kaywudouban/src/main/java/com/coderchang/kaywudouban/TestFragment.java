package com.coderchang.kaywudouban;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by coderchang on 2016/5/26.
 */
public class TestFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.my_image);
        //loadImageMethod(imageView);
        displayImageMethod(imageView);

        return view;
    }

    /**
     * loadImage方法
     * @param imageView
     */
    private void loadImageMethod(final ImageView imageView) {
        String imageUrl = "https://raw.githubusercontent.com/nostra13/Android-Universal-Image-Loader/master/UniversalImageLoader.png";
        ImageSize imageSize = new ImageSize(100, 100);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().loadImage(imageUrl, imageSize, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                imageView.setImageBitmap(loadedImage);
            }
        });
    }

    /**
     * displayImage方法
     */

    private void displayImageMethod(final ImageView imageView) {
        String imageUrl = "https://raw.githubusercontent.com/nostra13/Android-Universal-Image-Loader/master/UniversalImageLoader.png";
        ImageSize imageSize = new ImageSize(100, 100);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.apple_pic)
                .showImageOnFail(R.drawable.banana_pic)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(imageUrl, imageView,options);
    }
}
