package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;
import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 1/13/2018.
 */

public class RelatedVideoVideoHolder extends BaseViewHolder<StreamInfo> {
    private final ListStreamItemTestBinding mBinding;

    public RelatedVideoVideoHolder(ImageLoader imageLoader, ListStreamItemTestBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    public void bind(StreamInfo object) {

    }
}
