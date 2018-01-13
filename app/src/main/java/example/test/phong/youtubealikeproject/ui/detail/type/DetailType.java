package example.test.phong.youtubealikeproject.ui.detail.type;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 1/13/2018.
 */

public class DetailType implements BaseAdapterType {
    private final StreamInfo mData;

    public DetailType(StreamInfo data) {
        mData = data;
    }

    public StreamInfo getData() {
        return mData;
    }

    @Override
    public int getViewType() {
        return Constants.DETAIL_TYPE;
    }
}
