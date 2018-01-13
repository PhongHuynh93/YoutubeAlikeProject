package example.test.phong.youtubealikeproject.ui.detail.videoholder;

import android.content.Context;
import android.view.View;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import example.test.phong.youtubealikeproject.databinding.DetailVideoLayoutBinding;
import example.test.phong.youtubealikeproject.ui.detail.type.DetailType;
import example.test.phong.youtubealikeproject.util.Localization;

/**
 * Created by user on 1/13/2018.
 */

public class DetailViewHolder extends BaseViewHolder<DetailType> {
    private final DetailVideoLayoutBinding mDataBinding;

    public DetailViewHolder(DetailVideoLayoutBinding binding) {
        super(binding.getRoot());
        mDataBinding = binding;
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
//        mPresenter.formatDescription(description);
        // FIXME: 1/12/2018 make this oval shape
//        mImageLoader.displayImage(context, uploaderAvatarUrl, mDataBinding.imageViewUploader);
        // FIXME: 1/12/2018 make this has transition crossfade smoothly
//        mImageLoader.displayImageCrossFade(getContext(), thumbUrl, mDataBinding.itemThumbnailView);
    }

//    @Override
//    public void formatDescription(String descriptionHtml) {
//        if (TextUtils.isEmpty(descriptionHtml)) {
//            return;
//        }
//
//        mCompositeDisposable.add(Single.just(descriptionHtml)
//                .map(new Function<String, Spanned>() {
//                    @Override
//                    public Spanned apply(@NonNull String description) throws Exception {
//                        Spanned parsedDescription;
//                        if (Build.VERSION.SDK_INT >= 24) {
//                            parsedDescription = Html.fromHtml(description, 0);
//                        } else {
//                            //noinspection deprecation
//                            parsedDescription = Html.fromHtml(description);
//                        }
//                        return parsedDescription;
//                    }
//                })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Spanned>() {
//                    @Override
//                    public void accept(@NonNull Spanned spanned) throws Exception {
//                        mView.showDescription(spanned);
//                    }
//                }));
//    }
}
