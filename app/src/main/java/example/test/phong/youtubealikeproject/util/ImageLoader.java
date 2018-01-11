package example.test.phong.youtubealikeproject.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

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

    // // FIXME: 1/12/2018 the transitionCrossFade has flash animation
    public void displayImageCrossFade(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).transition(withCrossFade(2000)).into(imageView);
    }

    public void displayImage(Context context, String url, ImageView imageView, RequestListener<Drawable> listener) {
        Glide.with(context).load(url).listener(listener).into(imageView);
    }
}
