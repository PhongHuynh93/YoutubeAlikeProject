package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.view.ViewGroup;

import org.schabi.newpipe.extractor.InfoItem;

import example.test.phong.youtubealikeproject.R;

/**
 * Created by user on 12/30/2017.
 */

public class StreamInfoItemHolder extends InfoItemHolder {
    public StreamInfoItemHolder(InfoItemBuilder infoItemBuilder, ViewGroup parent) {
        super(infoItemBuilder, R.layout.list_stream_item, parent);
    }

    @Override
    public void updateFromItem(InfoItem infoItem) {

    }
}
