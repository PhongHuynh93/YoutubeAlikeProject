package example.test.phong.youtubealikeproject.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.BaseActivity;
import example.test.phong.youtubealikeproject.ui.viewmodel.VideoViewModel;
import example.test.phong.youtubealikeproject.util.ActivityUtils;

/**
 * Created by user on 1/7/2018.
 */

public class DetailActivity extends BaseActivity {
    public static final String ARG_DATA = "video data";
    private VideoModel mVideoModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        // Postpone the shared element enter transition.
        postponeEnterTransition();

        DetailFragment fragment =
                (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.root);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.root);
        }

        if (getIntent() != null) {
            if (getIntent().hasExtra(ARG_DATA)) {
                mVideoModel = getIntent().getExtras().getParcelable(ARG_DATA);
                VideoViewModel model = ViewModelProviders.of(this).get(VideoViewModel.class);
                model.setVideoModel(mVideoModel);
            } else {
                throw new IllegalArgumentException("Khong co intent nay thi class nay ko lam duoc");
                //                Timber.e("Khong co intent nay thi class nay ko lam duoc");
            }
        }
    }
}
