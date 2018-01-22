package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import example.test.phong.youtubealikeproject.databinding.DetailVideoLayoutBinding;
import example.test.phong.youtubealikeproject.ui.detail.type.DetailType;
import example.test.phong.youtubealikeproject.util.ImageLoader;
import example.test.phong.youtubealikeproject.util.Localization;

/**
 * Created by user on 1/13/2018.
 */

public class DetailViewHolder extends BaseViewHolder<DetailType> {
    private final DetailVideoLayoutBinding mDataBinding;
    private final ImageLoader mImageLoader;

    public DetailViewHolder(ImageLoader imageLoader, DetailVideoLayoutBinding binding) {
        super(binding.getRoot());
        mDataBinding = binding;
        mImageLoader = imageLoader;
    }

    @Override
    public void bind(DetailType detailType) {
        StreamInfo result = detailType.getData();

        long viewCount = result.getViewCount();

        String description = result.getDescription();
        // this is the HD of image -> show again to the cover picture and make the transition has crossfade to move smoothly from slow reso to high reso
        String thumbUrl = result.getThumbnailUrl();
        // load the uploader avatar and make the transform has oval shape.
        String uploaderAvatarUrl = result.getUploaderAvatarUrl();

        Context context = mDataBinding.getRoot().getContext();

        // the viewcount 20699438
        // after format 20,699,438 views
        if (viewCount >= 0) {
            mDataBinding.textViewViewCount.setText(Localization.localizeViewCount(context, viewCount));
            mDataBinding.textViewViewCount.setVisibility(View.VISIBLE);
        } else {
            mDataBinding.textViewViewCount.setVisibility(View.GONE);
        }

        // the description just contains the Html string, we just make that spanned style
        mDataBinding.textViewDescription.setText(formatDescription(description));
        // FIXME: 1/12/2018 make this oval shape
        mImageLoader.displayImage(context, uploaderAvatarUrl, mDataBinding.imageViewUploader);
        // FIXME: 1/12/2018 make this has transition crossfade smoothly
//        mImageLoader.displayImageCrossFade(context, thumbUrl, mDataBinding.itemThumbnailView);
    }

    public Spanned formatDescription(String description) {
        Spanned parsedDescription;
        if (Build.VERSION.SDK_INT >= 24) {
            parsedDescription = Html.fromHtml(description, 0);
        } else {
            //noinspection deprecation
            parsedDescription = Html.fromHtml(description);
        }
        return parsedDescription;
    }
}
