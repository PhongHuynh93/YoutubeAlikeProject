package example.test.phong.youtubealikeproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import example.test.phong.youtubealikeproject.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    @Inject
    YoutubeService mYoutubeService;

    private FragmentMainBinding mDatabinding;
    private ListVideoAdapter mAdapter;

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mDatabinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = mDatabinding.rcv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ListVideoAdapter();
        recyclerView.setAdapter(mAdapter);

        loadListVideo();
    }

    private void loadListVideo() {

    }
}
