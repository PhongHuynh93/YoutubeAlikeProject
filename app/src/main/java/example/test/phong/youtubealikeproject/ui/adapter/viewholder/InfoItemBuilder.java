package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.content.Context;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 12/30/2017.
 */

public class InfoItemBuilder {
    private final Context context;
    private final ImageLoader mImageLoader;
    private OnInfoItemSelectedListener<StreamInfoItem> onStreamSelectedListener;

    public interface OnInfoItemSelectedListener<T extends InfoItem> {
        void selected(T selectedItem);

        void held(T selectedItem);
    }

    @Inject
    public InfoItemBuilder(Context context, ImageLoader imageLoader) {
        this.context = context;
        mImageLoader = imageLoader;
    }

    public Context getContext() {
        return context;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public OnInfoItemSelectedListener<StreamInfoItem> getOnStreamSelectedListener() {
        return onStreamSelectedListener;
    }
}
