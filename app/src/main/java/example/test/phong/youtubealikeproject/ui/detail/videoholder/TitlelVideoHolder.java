package example.test.phong.youtubealikeproject.ui.detail.videoholder;

import example.test.phong.youtubealikeproject.databinding.TitleVideoLayoutBinding;
import example.test.phong.youtubealikeproject.ui.detail.type.TitleType;

/**
 * Created by user on 1/13/2018.
 */

public class TitlelVideoHolder extends BaseViewHolder<TitleType> {
    private final TitleVideoLayoutBinding mBinding;

    public TitlelVideoHolder(TitleVideoLayoutBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    public void bind(TitleType titleType) {
        mBinding.textViewTitle.setText(titleType.getData());
    }
}
