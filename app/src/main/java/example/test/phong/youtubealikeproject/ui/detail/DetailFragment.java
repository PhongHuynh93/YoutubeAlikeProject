package example.test.phong.youtubealikeproject.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.databinding.FragmentDetailBinding;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.BaseFragment;
import example.test.phong.youtubealikeproject.ui.DetailContract;
import example.test.phong.youtubealikeproject.ui.detail.type.DetailType;
import example.test.phong.youtubealikeproject.ui.viewmodel.VideoViewModel;
import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 1/7/2018.
 */

public class DetailFragment extends BaseFragment implements DetailContract.View {
    public static final String SHARED_IMAGE_KEY = "SHARED_IMAGE_KEY";
    private static final int MAX_TRANSITION_DELAY = 800;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    DetailContract.Presenter mPresenter;
    @Inject
    DetailVideoAdapter mAdapter;

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
                String name = videoModel.getName();

                RecyclerView recyclerView = mDataBinding.recyclerview.rcv;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(mAdapter);

//                mDataBinding.textViewTitle.setText(name);
                // Make sure that transition starts soon even if image is not ready.
                mDataBinding.itemThumbnailView.postDelayed(() -> getActivity().startPostponedEnterTransition(), MAX_TRANSITION_DELAY);
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

                startLoading(videoModel.getId(), videoModel.getUrl(), false);
            }
        });


    }

    /**
     * : you can verify whether or not allocating the OnPreDrawListener is needed by calling View#isLayoutRequested() beforehand, if necessary.
     * View#isLaidOut() may come in handy in some cases as well. ↩
     * <a href="https://www.androiddesignpatterns.com/2015/03/activity-postponed-shared-element-transitions-part3b.html"></a>
     */
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

    private void startLoading(int serviceId, String url, boolean forceLoad) {
        mPresenter.getData(serviceId, url, forceLoad);
    }

    @Override
    public void showData(StreamInfo result) {
        List<DetailType> detailTypes = new ArrayList<>();
        detailTypes.add(new DetailType(result));
        mAdapter.setData(detailTypes);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), "Calling on error " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void showDescription(Spanned spanned) {
//        mDataBinding.container.textViewDescription.setText(spanned);
//    }
}
