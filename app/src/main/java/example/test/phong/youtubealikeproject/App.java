package example.test.phong.youtubealikeproject;

import android.app.Activity;
import android.app.Application;

import org.schabi.newpipe.extractor.NewPipe;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import example.test.phong.youtubealikeproject.dagger.component.DaggerAppComponent;
import example.test.phong.youtubealikeproject.util.Downloader;
import example.test.phong.youtubealikeproject.util.NotLoggingTree;
import timber.log.Timber;

/**
 * Created by user on 12/30/2017.
 */

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    Downloader mDownloader;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        NewPipe.init(mDownloader);

        // in release, log nothing
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new NotLoggingTree());
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
