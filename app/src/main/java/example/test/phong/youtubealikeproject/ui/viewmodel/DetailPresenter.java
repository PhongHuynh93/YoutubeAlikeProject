package example.test.phong.youtubealikeproject.ui.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import org.schabi.newpipe.extractor.stream.StreamInfo;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.ui.DetailContract;
import example.test.phong.youtubealikeproject.util.CustomExtractorHelper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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

    @Override
    public void formatDescription(String descriptionHtml) {
        if (TextUtils.isEmpty(descriptionHtml)) {
            return;
        }

        mCompositeDisposable.add(Single.just(descriptionHtml)
                .map(new Function<String, Spanned>() {
                    @Override
                    public Spanned apply(@NonNull String description) throws Exception {
                        Spanned parsedDescription;
                        if (Build.VERSION.SDK_INT >= 24) {
                            parsedDescription = Html.fromHtml(description, 0);
                        } else {
                            //noinspection deprecation
                            parsedDescription = Html.fromHtml(description);
                        }
                        return parsedDescription;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Spanned>() {
                    @Override
                    public void accept(@NonNull Spanned spanned) throws Exception {
                        mView.showDescription(spanned);
                    }
                }));
    }
}
