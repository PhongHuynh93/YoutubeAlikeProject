package example.test.phong.youtubealikeproject.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.UrlIdHandler;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.kiosk.KioskInfo;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.ListVideoAdapter;
import example.test.phong.youtubealikeproject.R;
import example.test.phong.youtubealikeproject.databinding.RecyclerviewLayoutBinding;
import example.test.phong.youtubealikeproject.model.VideoModel;
import example.test.phong.youtubealikeproject.ui.BaseFragment;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.InfoItemBuilder;
import example.test.phong.youtubealikeproject.util.CustomExtractorHelper;
import example.test.phong.youtubealikeproject.util.NavigationHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 * // TODO: 1/12/2018 make room to save the last previous view 
 */
public class MainFragment extends BaseFragment {
    private static final String SERVICE = "SERVICE";
    private static final String KIOSID = "KIOSID";
    protected boolean DEBUG = MainActivity.DEBUG;

    @Inject
    CustomExtractorHelper mCustomExtractorHelper;
    @Inject
    InfoItemBuilder mInfoItemBuilder;

    private RecyclerviewLayoutBinding mDatabinding;
    private ListVideoAdapter mAdapter;
    private int mServiceId;
    private String mKiosId;
    private String mUrl;

    /**
     * @param serviceId
     * @param kiosId like Trending
     */
    public static MainFragment newInstance(int serviceId, String kiosId) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(SERVICE, serviceId);
        args.putString(KIOSID, kiosId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mServiceId = getArguments().getInt(SERVICE);
            mKiosId = getArguments().getString(KIOSID, "Trending");
            try {
//                mKiosId = NewPipe.getService(mServiceId)
//                        .getKioskList()
//                        .getDefaultKioskId();
                StreamingService service = NewPipe.getService(mServiceId);
                UrlIdHandler kioskTypeUrlIdHandler = service.getKioskList()
                        .getUrlIdHandlerByType(mKiosId);
                mUrl = kioskTypeUrlIdHandler.getUrl(mKiosId);
            } catch (ExtractionException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("Khong co intent nay thi class nay ko lam duoc");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabinding = DataBindingUtil.inflate(inflater, R.layout.recyclerview_layout, container, false);
        return mDatabinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = mDatabinding.rcv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ListVideoAdapter(mInfoItemBuilder);
        mAdapter.setOnStreamSelectedListener(new InfoItemBuilder.OnInfoItemSelectedListener<StreamInfoItem>() {
            @Override
            public void selected(View view, StreamInfoItem selectedItem) {
                // info - when select a video, open video to play
                NavigationHelper.openVideoDetail(getActivity(), view, new VideoModel(selectedItem.getServiceId(), selectedItem.getUrl(), selectedItem.getThumbnailUrl(), selectedItem.getName()));
            }

            @Override
            public void held(StreamInfoItem selectedItem) {

            }
        });
        recyclerView.setAdapter(mAdapter);

        // TODO: 12/30/2017 handle loadmore if size > 0
        loadListVideo();
    }

    // load list of videos from youtube
    // FIXME: 12/30/2017 move this into presenter
    private void loadListVideo() {
        String contentCountry = PreferenceManager
                .getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.search_language_key),
                        getString(R.string.default_language_value));
        mCustomExtractorHelper.getKioskInfo(mServiceId, mUrl, contentCountry, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<KioskInfo>() {
                    @Override
                    public void accept(KioskInfo result) throws Exception {
                        handleResult(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onError(throwable);
                        throwable.printStackTrace();
                    }
                });
    }

    private void handleResult(KioskInfo result) {
        // info - name like "Thịnh hành" -> set it in Toolbar
        mAdapter.addVideos(result.related_streams);
    }

    /**
     * Default implementation handles some general exceptions
     *
     * @return if the exception was handled
     * // TODO: 12/30/2017 copy from class BaseStateFragment
     */
    protected boolean onError(Throwable exception) {
        return true;
    }
}
