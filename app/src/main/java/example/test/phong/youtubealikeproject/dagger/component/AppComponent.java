package example.test.phong.youtubealikeproject.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import example.test.phong.youtubealikeproject.App;
import example.test.phong.youtubealikeproject.dagger.module.ActivityBuilder;
import example.test.phong.youtubealikeproject.dagger.module.AppModule;

/**
 * Created by user on 12/30/2017.
 */
// used in support Fragment so we change from AndroidInjectionModule to AndroidSupportInjectionModule
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
