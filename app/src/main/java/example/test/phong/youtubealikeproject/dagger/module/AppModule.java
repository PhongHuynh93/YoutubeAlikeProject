package example.test.phong.youtubealikeproject.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.test.phong.youtubealikeproject.YoutubeService;

/**
 * Created by user on 12/30/2017.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    YoutubeService provideApiService() {
        return new YoutubeService();
    }
}
