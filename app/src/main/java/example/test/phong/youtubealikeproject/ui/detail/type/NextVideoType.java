package example.test.phong.youtubealikeproject.ui.detail.type;

import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 1/22/2018.
 */

public class NextVideoType implements BaseAdapterType {
    private final StreamInfoItem data;

    public NextVideoType(StreamInfoItem streamInfoItem) {
        this.data = streamInfoItem;
    }

    @Override
    public int getViewType() {
        return Constants.NEXT_VIDEO_TYPE;
    }
}
