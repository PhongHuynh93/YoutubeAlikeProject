package example.test.phong.youtubealikeproject.ui.detail.type;

import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 1/13/2018.
 */

public class TitleType implements BaseAdapterType {
    private final String mName;

    public TitleType(String name) {
        mName = name;
    }

    public String getData() {
        return mName;
    }

    @Override
    public int getViewType() {
        return Constants.TITLE_VIDEO;
    }
}
