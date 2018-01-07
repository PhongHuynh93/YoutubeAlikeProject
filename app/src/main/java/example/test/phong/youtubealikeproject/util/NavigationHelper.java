package example.test.phong.youtubealikeproject.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.detail.DetailActivity;
import example.test.phong.youtubealikeproject.ui.detail.DetailFragment;

/**
 * Created by user on 1/7/2018.
 */

public class NavigationHelper {
    public static void openVideoDetail(Activity activity, View sharedView, VideoModel videoModel) {
        sharedView = sharedView.findViewById(R.id.itemThumbnailView);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_DATA, videoModel);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(activity, sharedView, DetailFragment.SHARED_IMAGE_KEY).toBundle();
        activity.startActivity(intent, bundle);
    }
}
