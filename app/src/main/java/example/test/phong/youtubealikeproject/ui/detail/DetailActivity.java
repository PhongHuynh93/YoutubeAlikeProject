package example.test.phong.youtubealikeproject.ui.detail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.ui.BaseActivity;
import example.test.phong.youtubealikeproject.util.ActivityUtils;

/**
 * Created by user on 1/7/2018.
 */

public class DetailActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.content_main);

        DetailFragment fragment =
                (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.root);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.root);
        }
    }
}
