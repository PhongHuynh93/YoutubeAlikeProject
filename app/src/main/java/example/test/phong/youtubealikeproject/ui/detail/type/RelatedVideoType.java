package example.test.phong.youtubealikeproject.ui.detail.type;

import org.schabi.newpipe.extractor.InfoItem;

import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 1/13/2018.
 */

public class RelatedVideoType implements BaseAdapterType {
    private final InfoItem data;

    public RelatedVideoType(InfoItem infoItem) {
        this.data = infoItem;
    }

    @Override
    public int getViewType() {
        return Constants.RELATED_VIDEO_TYPE;
    }
}
