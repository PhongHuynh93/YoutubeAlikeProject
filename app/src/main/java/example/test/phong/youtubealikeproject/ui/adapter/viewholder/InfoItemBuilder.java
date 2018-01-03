package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by user on 12/30/2017.
 */

public class InfoItemBuilder {
    private final Context context;

    @Inject
    public InfoItemBuilder(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
