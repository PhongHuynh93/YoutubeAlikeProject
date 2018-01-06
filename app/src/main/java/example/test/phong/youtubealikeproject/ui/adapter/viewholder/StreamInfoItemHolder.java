package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.text.TextUtils;
import android.widget.TextView;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;
import example.test.phong.youtubealikeproject.util.Localization;

/**
 * Created by user on 12/30/2017.
 */

public class StreamInfoItemHolder extends StreamMiniInfoItemHolder {
    public final TextView itemAdditionalDetails;

    public StreamInfoItemHolder(InfoItemBuilder infoItemBuilder, ListStreamItemTestBinding binding) {
        super(infoItemBuilder, binding);
        itemAdditionalDetails = binding.itemAdditionalDetails;
    }

    @Override
    public void updateFromItem(InfoItem infoItem) {
        super.updateFromItem(infoItem);

        if (!(infoItem instanceof StreamInfoItem)) return;
        final StreamInfoItem item = (StreamInfoItem) infoItem;

        itemAdditionalDetails.setText(getStreamInfoDetailLine(item));
    }

    private String getStreamInfoDetailLine(final StreamInfoItem infoItem) {
        String viewsAndDate = "";
        if (infoItem.view_count >= 0) {
            viewsAndDate = Localization.shortViewCount(itemBuilder.getContext(), infoItem.view_count);
        }
        if (!TextUtils.isEmpty(infoItem.upload_date)) {
            if (viewsAndDate.isEmpty()) {
                viewsAndDate = infoItem.upload_date;
            } else {
                viewsAndDate += " • " + infoItem.upload_date;
            }
        }
        return viewsAndDate;
    }
}
