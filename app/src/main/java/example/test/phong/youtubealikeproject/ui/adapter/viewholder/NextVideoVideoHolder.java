package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;
import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 1/13/2018.
 */

public class NextVideoVideoHolder extends BaseViewHolder<StreamInfoItem> {
    private final ListStreamItemTestBinding mBinding;

    public NextVideoVideoHolder(ImageLoader imageLoader, ListStreamItemTestBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    public void bind(StreamInfoItem object) {

    }
}
