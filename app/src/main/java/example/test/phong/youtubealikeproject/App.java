package example.test.phong.youtubealikeproject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import example.test.phong.youtubealikeproject.dagger.component.DaggerAppComponent;

/**
 * Created by user on 12/30/2017.
 */

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
