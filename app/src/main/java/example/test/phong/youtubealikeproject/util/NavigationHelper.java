package example.test.phong.youtubealikeproject.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.detail.DetailActivity;
import example.test.phong.youtubealikeproject.ui.detail.DetailFragment;

/**
 * Created by user on 1/7/2018.
 */

public class NavigationHelper {

    /**
     * fix flash status bar by set it as shared element
     * <a href="https://stackoverflow.com/questions/26600263/how-do-i-prevent-the-status-bar-and-navigation-bar-from-animating-during-an-acti"></a>
     *
     * @param activity
     * @param sharedView
     * @param videoModel
     */
    public static void openVideoDetail(Activity activity, View sharedView, VideoModel videoModel) {
        sharedView = sharedView.findViewById(R.id.itemThumbnailView);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_DATA, videoModel);
        View navigationBar = activity.findViewById(android.R.id.navigationBarBackground);
        View statusBar = activity.findViewById(android.R.id.statusBarBackground);
        List<Pair<View, String>> pairs = new ArrayList<>();
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }
        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }
        pairs.add(Pair.create(sharedView, DetailFragment.SHARED_IMAGE_KEY));

        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(activity, pairs.toArray(new Pair[pairs.size()])).toBundle();
        activity.startActivity(intent, bundle);
    }
}
