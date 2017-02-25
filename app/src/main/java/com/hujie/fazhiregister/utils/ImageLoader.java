package com.hujie.fazhiregister.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.hujie.fazhiregister.R;

/**
 * Created by hujie on 2017/2/24.
 */

public class ImageLoader {
    public static void LoadIcon(Context context, String path, ImageView imageView){
        Glide.with(context).load(path).transform(new CycleImage(context)).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
