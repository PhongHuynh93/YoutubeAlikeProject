package example.test.phong.youtubealikeproject.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.test.phong.youtubealikeproject.util.CustomExtractorHelper;
import example.test.phong.youtubealikeproject.util.InfoCache;
import example.test.phong.youtubealikeproject.util.SubscriptionService;

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
    SubscriptionService provideApiService() {
        return new SubscriptionService();
    }

    @Provides
    @Singleton
    InfoCache provideInfoCache() {
        return new InfoCache();
    }

    @Provides
    @Singleton
    CustomExtractorHelper provideCustomExtractorHelper(InfoCache infoCache) {
        return new CustomExtractorHelper(infoCache);
    }
}
