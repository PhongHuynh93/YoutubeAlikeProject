package example.test.phong.youtubealikeproject.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import example.test.phong.youtubealikeproject.ui.main.MainFragment;

/**
 * Created by user on 12/30/2017.
 */

@Module
public abstract class MainFragmentProvider {
    // create subcomponent for fragment
    @ContributesAndroidInjector(modules = {MainFragmentModule.class})
    abstract MainFragment bindMainFragment();
}