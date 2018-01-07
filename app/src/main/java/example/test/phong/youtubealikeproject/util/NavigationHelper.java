package example.test.phong.youtubealikeproject.util;

import android.app.Activity;
import android.content.Intent;

import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.detail.DetailActivity;

/**
 * Created by user on 1/7/2018.
 */

public class NavigationHelper {
    public static void openVideoDetail(Activity activity, VideoModel videoModel) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_DATA, videoModel);
        activity.startActivity(intent);
    }
}
