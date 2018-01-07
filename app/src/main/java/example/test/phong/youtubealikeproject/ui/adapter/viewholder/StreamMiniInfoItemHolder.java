package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.support.v4.content.ContextCompat;
import android.view.View;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamType;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;
import example.test.phong.youtubealikeproject.util.Localization;

/**
 * Created by user on 1/6/2018.
 */

public class StreamMiniInfoItemHolder extends InfoItemHolder {
    private final ListStreamItemTestBinding mBinding;

    StreamMiniInfoItemHolder(InfoItemBuilder infoItemBuilder, ListStreamItemTestBinding binding) {
        super(infoItemBuilder, binding);
        mBinding = binding;
    }

    @Override
    public void updateFromItem(InfoItem infoItem) {
        if (!(infoItem instanceof StreamInfoItem)) return;
        final StreamInfoItem item = (StreamInfoItem) infoItem;

        mBinding.itemVideoTitleView.setText(item.getName());
        mBinding.itemUploaderView.setText(item.uploader_name);

        if (item.duration > 0) {
            mBinding.itemDurationView.setText(Localization.getDurationString(item.duration));
            mBinding.itemDurationView.setBackgroundColor(ContextCompat.getColor(itemBuilder.getContext(), R.color.duration_background_color));
            mBinding.itemDurationView.setVisibility(View.VISIBLE);
        } else if (item.stream_type == StreamType.LIVE_STREAM) {
            mBinding.itemDurationView.setText(R.string.duration_live);
            mBinding.itemDurationView.setBackgroundColor(ContextCompat.getColor(itemBuilder.getContext(), R.color.live_duration_background_color));
            mBinding.itemDurationView.setVisibility(View.VISIBLE);
        } else {
            mBinding.itemDurationView.setVisibility(View.GONE);
        }

        // Default thumbnail is shown on error, while loading and if the url is empty
        itemBuilder.getImageLoader()
                .displayImage(mBinding.itemThumbnailView.getContext(), item.thumbnail_url, mBinding.itemThumbnailView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemBuilder.getOnStreamSelectedListener() != null) {
                    itemBuilder.getOnStreamSelectedListener().selected(view, item);
                }
            }
        });

        switch (item.stream_type) {
            case AUDIO_STREAM:
            case VIDEO_STREAM:
            case FILE:
                enableLongClick(item);
                break;
            case LIVE_STREAM:
            case AUDIO_LIVE_STREAM:
            case NONE:
            default:
                disableLongClick();
                break;
        }
    }

    private void disableLongClick() {
        itemView.setLongClickable(false);
        itemView.setOnLongClickListener(null);
    }

    private void enableLongClick(StreamInfoItem item) {
        itemView.setLongClickable(true);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemBuilder.getOnStreamSelectedListener() != null) {
                    itemBuilder.getOnStreamSelectedListener().held(item);
                }
                return true;
            }
        });
    }
}
