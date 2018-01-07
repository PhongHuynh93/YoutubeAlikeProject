package example.test.phong.youtubealikeproject.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.databinding.FragmentDetailBinding;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.BaseFragment;
import example.test.phong.youtubealikeproject.ui.viewmodel.VideoViewModel;
import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 1/7/2018.
 */

public class DetailFragment extends BaseFragment {
    public static final String SHARED_IMAGE_KEY = "SHARED_IMAGE_KEY";
    private static final int MAX_TRANSITION_DELAY = 800;
    @Inject
    ImageLoader mImageLoader;

    private FragmentDetailBinding mDataBinding;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        mDataBinding.itemThumbnailView.setTransitionName(SHARED_IMAGE_KEY);
        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity()).get(VideoViewModel.class).getVideoModel().observe(this, new Observer<VideoModel>() {
            @Override
            public void onChanged(@Nullable VideoModel videoModel) {
                String url = videoModel.getImageUrl();
                // Make sure that transition starts soon even if image is not ready.
                mDataBinding.itemThumbnailView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().startPostponedEnterTransition();
                    }
                }, MAX_TRANSITION_DELAY);
                mImageLoader.displayImage(getContext(), url, mDataBinding.itemThumbnailView, new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        startPostponedEnterTransitionSafe();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        startPostponedEnterTransitionSafe();
                        return false;
                    }
                });
            }
        });
    }

    private void startPostponedEnterTransitionSafe() {
        if (mDataBinding.itemThumbnailView.isLayoutRequested()) {
            mDataBinding.itemThumbnailView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mDataBinding.itemThumbnailView.getViewTreeObserver().removeOnPreDrawListener(this);
                    getActivity().startPostponedEnterTransition();
                    return true;
                }
            });
        } else {
            getActivity().startPostponedEnterTransition();
        }
    }
}
