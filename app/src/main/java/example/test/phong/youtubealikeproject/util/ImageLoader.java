package example.test.phong.youtubealikeproject.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by user on 1/6/2018.
 * sum of glide configuration
 */

@Singleton
public class ImageLoader {
    @Inject
    public ImageLoader() {
    }


    public void displayImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
