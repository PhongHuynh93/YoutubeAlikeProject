package example.test.phong.youtubealikeproject.dagger.module;

import dagger.Binds;
import dagger.Module;
import example.test.phong.youtubealikeproject.ui.DetailContract;
import example.test.phong.youtubealikeproject.ui.detail.DetailFragment;
import example.test.phong.youtubealikeproject.ui.viewmodel.DetailPresenter;

/**
 * Created by user on 1/11/2018.
 */
@Module
public abstract class DetailModule {
    @Binds
    public abstract DetailContract.Presenter provideDetailPresenter(DetailPresenter loginPresenter);

    @Binds
    public abstract DetailContract.View provideDetailView(DetailFragment fragment);
}
