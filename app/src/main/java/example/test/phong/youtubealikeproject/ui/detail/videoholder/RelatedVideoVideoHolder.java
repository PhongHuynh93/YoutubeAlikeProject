package example.test.phong.youtubealikeproject.ui.detail.videoholder;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import example.test.phong.youtubealikeproject.databinding.DetailRelatedVideoLayoutBinding;

/**
 * Created by user on 1/13/2018.
 */

public class RelatedVideoVideoHolder extends BaseViewHolder<StreamInfo> {
    private final DetailRelatedVideoLayoutBinding mBinding;

    public RelatedVideoVideoHolder(DetailRelatedVideoLayoutBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    public void bind(StreamInfo object) {

    }
}
