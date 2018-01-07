package example.test.phong.youtubealikeproject.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import example.test.phong.youtubealikeproject.ui.main.MainActivity;

/**
 * Created by user on 12/30/2017.
 */
@Module
public abstract class ActivityBuilder {
    // create subcomponent for activity
    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainFragmentProvider.class})
    abstract MainActivity bindMainActivity();
}