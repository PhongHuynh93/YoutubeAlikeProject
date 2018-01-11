package example.test.phong.youtubealikeproject.ui.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.ui.DetailContract;
import example.test.phong.youtubealikeproject.util.CustomExtractorHelper;

/**
 * Created by user on 1/10/2018.
 */

public class DetailPresenter extends ViewModel implements LifecycleObserver, DetailContract.Presenter {
    private final CustomExtractorHelper mExtractorHelper;

    @Inject
    public DetailPresenter(CustomExtractorHelper extractorHelper, DetailContract.View view) {
        mExtractorHelper = extractorHelper;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void getData(int serviceId, String url, boolean forceLoad) {
//        mExtractorHelper.getStreamInfo(serviceId, url, forceLoad)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<StreamInfo>() {
//                    @Override
//                    public void accept(@NonNull StreamInfo result) throws Exception {
//                        isLoading.set(false);
//                        currentInfo = result;
//                        showContentWithAnimation(120, 0, 0);
//                        handleResult(result);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        isLoading.set(false);
//                        onError(throwable);
//                    }
//                });
    }
}
