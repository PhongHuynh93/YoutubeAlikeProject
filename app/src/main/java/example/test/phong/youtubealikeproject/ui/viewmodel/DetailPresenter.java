package example.test.phong.youtubealikeproject.ui.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.ui.DetailContract;
import example.test.phong.youtubealikeproject.util.CustomExtractorHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 1/10/2018.
 */

public class DetailPresenter extends ViewModel implements LifecycleObserver, DetailContract.Presenter {
    private final CustomExtractorHelper mExtractorHelper;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final DetailContract.View mView;

    @Inject
    public DetailPresenter(CustomExtractorHelper extractorHelper, DetailContract.View view) {
        mExtractorHelper = extractorHelper;
        mView = view;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onAttach() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDetach() {
        // Clean up your resources here
        mCompositeDisposable.clear();
    }

    @Override
    public void getData(int serviceId, String url, boolean forceLoad) {
        mCompositeDisposable.add(mExtractorHelper.getStreamInfo(serviceId, url, forceLoad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StreamInfo>() {
                    @Override
                    public void accept(@NonNull StreamInfo result) throws Exception {
                        mView.showData(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                }));
    }
}
